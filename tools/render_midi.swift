#!/usr/bin/env swift

import AudioToolbox
import AVFAudio
import Foundation

enum RenderError: Error, CustomStringConvertible {
    case invalidArguments
    case invalidMIDI
    case missingSoundBank(String)
    case trackChannelMismatch(tracks: Int, channels: Int)
    case emptySequence
    case renderFailed(AVAudioEngineManualRenderingStatus)

    var description: String {
        switch self {
        case .invalidArguments:
            return "usage: swift Tools/render_midi.swift INPUT.mid OUTPUT.caf"
        case .invalidMIDI:
            return "invalid Standard MIDI File"
        case let .missingSoundBank(path):
            return "General MIDI sound bank is missing at \(path)"
        case let .trackChannelMismatch(tracks, channels):
            return "sequencer created \(tracks) tracks for \(channels) MIDI channels"
        case .emptySequence:
            return "MIDI sequence has no playable duration"
        case let .renderFailed(status):
            return "offline render failed with status \(status.rawValue)"
        }
    }
}

struct MIDIChannel {
    let number: UInt8
    let program: UInt8
}

struct MIDIReader {
    let data: Data
    var offset = 0

    mutating func readByte() throws -> UInt8 {
        guard offset < data.count else { throw RenderError.invalidMIDI }
        defer { offset += 1 }
        return data[offset]
    }

    mutating func readUInt16() throws -> Int {
        Int(try readByte()) << 8 | Int(try readByte())
    }

    mutating func readUInt32() throws -> Int {
        Int(try readByte()) << 24 | Int(try readByte()) << 16 | Int(try readByte()) << 8 | Int(try readByte())
    }

    mutating func readVariableLength() throws -> Int {
        var value = 0
        for _ in 0..<4 {
            let byte = try readByte()
            value = value << 7 | Int(byte & 0x7f)
            if byte & 0x80 == 0 { return value }
        }
        throw RenderError.invalidMIDI
    }

    mutating func expect(_ bytes: [UInt8]) throws {
        for byte in bytes {
            guard try readByte() == byte else { throw RenderError.invalidMIDI }
        }
    }

    mutating func skip(_ count: Int) throws {
        guard count >= 0, offset + count <= data.count else { throw RenderError.invalidMIDI }
        offset += count
    }
}

func midiChannels(in url: URL) throws -> [MIDIChannel] {
    var reader = MIDIReader(data: try Data(contentsOf: url))
    try reader.expect(Array("MThd".utf8))
    let headerLength = try reader.readUInt32()
    guard headerLength >= 6 else { throw RenderError.invalidMIDI }
    _ = try reader.readUInt16()
    let trackCount = try reader.readUInt16()
    _ = try reader.readUInt16()
    try reader.skip(headerLength - 6)

    var channels = Set<UInt8>()
    var programs: [UInt8: [UInt8]] = [:]
    for _ in 0..<trackCount {
        try reader.expect(Array("MTrk".utf8))
        let trackLength = try reader.readUInt32()
        let trackEnd = reader.offset + trackLength
        guard trackEnd <= reader.data.count else { throw RenderError.invalidMIDI }
        var runningStatus: UInt8?
        while reader.offset < trackEnd {
            _ = try reader.readVariableLength()
            var status = try reader.readByte()
            var firstDataByte: UInt8?
            if status < 0x80 {
                guard let runningStatus else { throw RenderError.invalidMIDI }
                firstDataByte = status
                status = runningStatus
            } else if status < 0xf0 {
                runningStatus = status
            }

            if status == 0xff {
                _ = try reader.readByte()
                try reader.skip(try reader.readVariableLength())
                continue
            }
            if status == 0xf0 || status == 0xf7 {
                try reader.skip(try reader.readVariableLength())
                runningStatus = nil
                continue
            }
            guard status < 0xf0 else { throw RenderError.invalidMIDI }

            let channel = status & 0x0f
            channels.insert(channel)
            let command = status & 0xf0
            let dataLength = command == 0xc0 || command == 0xd0 ? 1 : 2
            let first: UInt8
            if let firstDataByte {
                first = firstDataByte
            } else {
                first = try reader.readByte()
            }
            if command == 0xc0 {
                if programs[channel, default: []].contains(first) == false {
                    programs[channel, default: []].append(first)
                }
            }
            if dataLength == 2 {
                _ = try reader.readByte()
            }
        }
        reader.offset = trackEnd
    }
    return channels.sorted().map { channel in
        let channelPrograms = programs[channel, default: [0]]
        // The sequencer forwards later program changes to this channel's sampler.
        return MIDIChannel(number: channel, program: channelPrograms[0])
    }
}

func renderMIDI(inputURL: URL, outputURL: URL) throws {
    let channels = try midiChannels(in: inputURL)
    let soundBankPath = ProcessInfo.processInfo.environment["AE_DLS_SOUNDBANK"]
        ?? "/System/Library/Components/CoreAudio.component/Contents/Resources/gs_instruments.dls"
    guard FileManager.default.fileExists(atPath: soundBankPath) else {
        throw RenderError.missingSoundBank(soundBankPath)
    }
    let soundBankURL = URL(fileURLWithPath: soundBankPath)

    let engine = AVAudioEngine()
    let samplers = try channels.map { channel in
        let sampler = AVAudioUnitSampler()
        engine.attach(sampler)
        engine.connect(sampler, to: engine.mainMixerNode, format: nil)
        try sampler.loadSoundBankInstrument(
            at: soundBankURL,
            program: channel.program,
            bankMSB: channel.number == 9 ? UInt8(kAUSampler_DefaultPercussionBankMSB) : UInt8(kAUSampler_DefaultMelodicBankMSB),
            bankLSB: UInt8(kAUSampler_DefaultBankLSB)
        )
        return sampler
    }

    let sequencer = AVAudioSequencer(audioEngine: engine)
    try sequencer.load(from: inputURL, options: AVMusicSequenceLoadOptions(rawValue: 1))
    guard sequencer.tracks.count >= samplers.count else {
        throw RenderError.trackChannelMismatch(tracks: sequencer.tracks.count, channels: samplers.count)
    }
    // Channel splitting retains leading tempo/meta tracks, which contain no notes.
    for (track, sampler) in zip(sequencer.tracks.suffix(samplers.count), samplers) {
        track.destinationAudioUnit = sampler
    }

    let duration = sequencer.tracks.map(\.lengthInSeconds).max() ?? 0
    guard duration > 0 else { throw RenderError.emptySequence }

    let sampleRate = 44_100.0
    let format = try AVAudioFormat(
        commonFormat: .pcmFormatFloat32,
        sampleRate: sampleRate,
        channels: 2,
        interleaved: false
    ).unwrap(or: RenderError.invalidMIDI)
    try engine.enableManualRenderingMode(.offline, format: format, maximumFrameCount: 4_096)
    let outputSettings: [String: Any] = [
        AVFormatIDKey: kAudioFormatLinearPCM,
        AVSampleRateKey: sampleRate,
        AVNumberOfChannelsKey: 2,
        AVLinearPCMBitDepthKey: 32,
        AVLinearPCMIsFloatKey: true,
        AVLinearPCMIsNonInterleaved: false,
    ]
    let output = try AVAudioFile(forWriting: outputURL, settings: outputSettings)

    try engine.start()
    sequencer.prepareToPlay()
    sequencer.currentPositionInSeconds = 0
    try sequencer.start()

    let targetFrames = AVAudioFramePosition((duration * sampleRate).rounded(.up))
    let buffer = try AVAudioPCMBuffer(
        pcmFormat: engine.manualRenderingFormat,
        frameCapacity: engine.manualRenderingMaximumFrameCount
    ).unwrap(or: RenderError.invalidMIDI)

    while engine.manualRenderingSampleTime < targetFrames {
        let remaining = targetFrames - engine.manualRenderingSampleTime
        let frames = min(AVAudioFrameCount(remaining), engine.manualRenderingMaximumFrameCount)
        switch try engine.renderOffline(frames, to: buffer) {
        case .success:
            try output.write(from: buffer)
        case .cannotDoInCurrentContext:
            continue
        case .insufficientDataFromInputNode:
            buffer.frameLength = frames
            for channel in 0..<Int(buffer.format.channelCount) {
                buffer.floatChannelData?[channel].initialize(repeating: 0, count: Int(frames))
            }
            try output.write(from: buffer)
        case let status:
            throw RenderError.renderFailed(status)
        }
    }

    sequencer.stop()
    engine.stop()
}

extension Optional {
    func unwrap(or error: @autoclosure () -> Error) throws -> Wrapped {
        guard let self else { throw error() }
        return self
    }
}

do {
    guard CommandLine.arguments.count == 3 else { throw RenderError.invalidArguments }
    try renderMIDI(
        inputURL: URL(fileURLWithPath: CommandLine.arguments[1]),
        outputURL: URL(fileURLWithPath: CommandLine.arguments[2])
    )
} catch {
    FileHandle.standardError.write(Data("render_midi: \(error)\n".utf8))
    exit(1)
}

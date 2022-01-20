package com.liverary.book.springboot.service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import java.util.List;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import java.io.FileOutputStream;
import java.io.OutputStream;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.TargetDataLine;

import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.*;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;

public class TTSService {

//    public static void mainob() { //String[] args
//        ResponseObserver<StreamingRecognizeResponse> responseObserver = null;
//        try (SpeechClient client = SpeechClient.create()) {
//
//            responseObserver = new ResponseObserver<StreamingRecognizeResponse>() {
//                ArrayList<StreamingRecognizeResponse> responses = new ArrayList<>();
//
//                public void onStart(StreamController controller) {
//                }
//
//                public void onResponse(StreamingRecognizeResponse response) {
//                    responses.add(response);
//                }
//
//                public void onComplete() {
//                    String record = "";
//
//                    for (StreamingRecognizeResponse response : responses) {
//                        StreamingRecognitionResult result = response.getResultsList().get(0);
//                        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
//                        record += alternative.getTranscript();
//                        System.out.printf("Transcript : %s\n", alternative.getTranscript());
//                    }
//                }
//
//                public void onError(Throwable t) {
//                    System.out.println(t);
//                }
//            };
//
//            ClientStream<StreamingRecognizeRequest> clientStream = client.streamingRecognizeCallable()
//                    .splitCall(responseObserver);
//
//            RecognitionConfig recognitionConfig = RecognitionConfig.newBuilder()
//                    .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16).setLanguageCode("ko-KR")// 한국어 설정
//                    .setSampleRateHertz(16000).build();
//            StreamingRecognitionConfig streamingRecognitionConfig = StreamingRecognitionConfig.newBuilder()
//                    .setConfig(recognitionConfig).build();
//
//            StreamingRecognizeRequest request = StreamingRecognizeRequest.newBuilder()
//                    .setStreamingConfig(streamingRecognitionConfig).build(); // The first request in a streaming call
//            // has to be a config
//
//            clientStream.send(request);
//            // SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed:
//            // true,
//            // bigEndian: false
//            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
//            DataLine.Info targetInfo = new Info(TargetDataLine.class, audioFormat); // Set the system information to
//            // read from the microphone audio
//            // stream
//
//            if (!AudioSystem.isLineSupported(targetInfo)) {
//                System.out.println("Microphone not supported");
//                System.exit(0);
//            }
//            // Target data line captures the audio stream the microphone produces.
//            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
//            targetDataLine.open(audioFormat);
//            targetDataLine.start();
//            System.out.println("Start speaking");
//            long startTime = System.currentTimeMillis();
//            // Audio Input Stream
//            AudioInputStream audio = new AudioInputStream(targetDataLine);
//            while (true) {
//                long estimatedTime = System.currentTimeMillis() - startTime;
//                byte[] data = new byte[6400];
//                audio.read(data);
//                if (estimatedTime > 6000) { // 60 seconds 스트리밍 하는 시간설정(기본 60초)
//                    System.out.println("Stop speaking.");
//                    targetDataLine.stop();
//                    targetDataLine.close();
//                    break;
//                }
//                request = StreamingRecognizeRequest.newBuilder().setAudioContent(ByteString.copyFrom(data)).build();
//                clientStream.send(request);
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        responseObserver.onComplete(); //완료 이벤트 발생
//    }

    /** Demonstrates using the Speech API to transcribe an audio file. */
    public static void mainob() throws Exception {
        // Instantiates a client
        try (SpeechClient speechClient = SpeechClient.create()) {

            // The path to the audio file to transcribe
            String gcsUri = "gs://cloud-samples-data/speech/brooklyn_bridge.raw";

            // Builds the sync recognize request
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(AudioEncoding.LINEAR16)
                            .setSampleRateHertz(16000)
                            .setLanguageCode("en-US")
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
            }
        }
    }

    /**
     * Demonstrates using the Text to Speech client to synthesize text or ssml.
     *
     * @param text the raw text to be synthesized. (e.g., "Hello there!")
     * @throws Exception on TextToSpeechClient Errors.
     */
    public static void synthesizeText(String text) throws Exception {
        // Instantiates a client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            // Build the voice request
            VoiceSelectionParams voice =
                    VoiceSelectionParams.newBuilder()
                            .setLanguageCode("en-US") // languageCode = "en_us"
                            .setSsmlGender(SsmlVoiceGender.FEMALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
                            .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig =
                    AudioConfig.newBuilder()
                            .setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
                            .build();

            // Perform the text-to-speech request
            SynthesizeSpeechResponse response =
                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();

            // Write the response to the output file.
            try (OutputStream out = new FileOutputStream("output.mp3")) {
                out.write(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.mp3\"");
            }
        }
    }

}
package dp;

import java.io.File;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class ChainOfResp_1 {

    public static Optional<String> parseText(File file) {
        return Optional.ofNullable(file)
                .filter(f -> f.getType() == File.Type.TEXT)
                .map(f -> "Text file: " + f.getContent());
    }

    public static Optional<String> parsePresentation(File file) {
        return Optional.ofNullable(file)
                .filter(f -> f.getType() == File.Type.PRESENTATION)
                .map(f -> "Presentation file: " + f.getContent());
    }

    public static Optional<String> parseAudio(File file) {
        return Optional.ofNullable(file)
                .filter(f -> f.getType() == File.Type.AUDIO)
                .map(f -> "Audio file: " + f.getContent());
    }

    public static Optional<String> parseVideo(File file) {
        return Optional.ofNullable(file)
                .filter(f -> f.getType() == File.Type.VIDEO)
                .map(f -> "Video file: " + f.getContent());
    }

    public static void main(String[] args) {
        File file = new File(File.Type.AUDIO, "Dream Theater  - The Astonishing");
        ChainOfResp_1.parseAudio(new File(""));
        Function<File, Optional<String>> parseAudio = ChainOfResp_1::parseAudio;

        System.out.println(
                Stream.<Function<File, Optional<String>>>of(
                                ChainOfResp_1::parseText,
                                ChainOfResp_1::parsePresentation,
                                ChainOfResp_1::parseAudio,
                                f -> ChainOfResp_1.parseVideo(f))
                        .map(f -> f.apply(file))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Unknown file: " + file))
        );
    }

}

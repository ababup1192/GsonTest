import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

public class FileReader {

    /**
     * ファイルを読み込んで内容をStringにして返す。
     *
     * @param resourceName resourcesディレクトリ化のファイル名(ディレクトリの中にあるときはスラッシュで区切る)
     * @return ファイルの内容
     */
    public static Optional<String> fileRead(String resourceName) {
        try {
            // resourcesからファイル読み込み
            Path path = Paths.get(ClassLoader.getSystemResource(resourceName).toURI());

            // 行ごと読み込み
            try (Stream<String> lines = Files.lines(path)) {
                // 改行入れつつ 足し込み
                return Optional.of(lines.reduce((s, s2) -> s + "\n" + s2).get());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException | NullPointerException e) {
            e.printStackTrace();
        }
        // 失敗時はempty返す
        return Optional.empty();
    }
}

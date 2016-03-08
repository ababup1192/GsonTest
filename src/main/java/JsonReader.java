import com.google.gson.Gson;

import java.util.Optional;

public class JsonReader {

    /**
     * Jsonが記述されているファイルを読み込んで、Javaオブジェクトに変換する
     *
     * @param resourceName リソース名
     * @param entityClass  resourcesディレクトリのファイル名(ディレクトリの中にあるときはスラッシュで区切る)
     * @param <T>          Javaオブジェクトの型
     * @return Optional[T]
     */
    public static <T> Optional<T> readGson(String resourceName, Class<T> entityClass) {
        return FileReader.fileRead(resourceName).map(json -> {
            Gson gson = new Gson();
            return Optional.ofNullable(gson.fromJson(json, entityClass));
        }).orElse(Optional.empty());
    }
}

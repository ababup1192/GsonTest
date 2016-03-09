import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
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
    public static <T> Optional<T> readJsonObject(String resourceName, Class<T> entityClass) {
        return FileReader.fileRead(resourceName).map(json -> {
            Gson gson = new Gson();
            return Optional.ofNullable(gson.fromJson(json, entityClass));
        }).orElse(Optional.empty());
    }

    public static <T> List<T> readJsonObjects(String resourceName, Class<T> entityClass) {
        return FileReader.fileRead(resourceName).map(json ->
                fromJsonList(json, entityClass))
                .orElse(Collections.emptyList());
    }

    private static <T> List<T> fromJsonList(String json, Class<T> entityClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, new ListOfSomething<>(entityClass));
    }

    private static class ListOfSomething<X> implements ParameterizedType {

        private Class<?> wrapped;

        public ListOfSomething(Class<X> wrapped) {
            this.wrapped = wrapped;
        }

        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        public Type getRawType() {
            return List.class;
        }

        public Type getOwnerType() {
            return null;
        }

    }
}

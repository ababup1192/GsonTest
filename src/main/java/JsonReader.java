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
     * @param resourceName resourcesディレクトリのファイル名(ディレクトリの中にあるときはスラッシュで区切る)
     * @param entityClass  変換される対象のオブジェクトのクラス
     * @param <T>          Javaオブジェクトの型
     * @return Optional[T]
     */
    public static <T> Optional<T> readJsonObject(String resourceName, Class<T> entityClass) {
        return FileReader.fileRead(resourceName).map(json -> {
            Gson gson = new Gson();
            return Optional.ofNullable(gson.fromJson(json, entityClass));
        }).orElse(Optional.empty());
    }

    /**
     * Jsonが記述されているファイルを読み込んで、Java Listオブジェクトに変換する
     *
     * @param resourceName resourcesディレクトリのファイル名(ディレクトリの中にあるときはスラッシュで区切る)
     * @param entityClass  変換される対象のオブジェクトのクラス
     * @param <T>          Javaオブジェクトの型
     * @return List[T]
     */
    public static <T> List<T> readJsonObjects(String resourceName, Class<T> entityClass) {
        return FileReader.fileRead(resourceName).map(json ->
                fromJsonList(json, entityClass))
                .orElse(Collections.emptyList());
    }

    /**
     * Json文字列から Java Listオブジェクトに変換する
     *
     * @param json        Json文字列
     * @param entityClass 変換される対象のオブジェクトのクラス
     * @param <T>         Javaオブジェクトの型
     * @return List[T]
     */
    private static <T> List<T> fromJsonList(String json, Class<T> entityClass) {
        Gson gson = new Gson();
        return gson.fromJson(json, new ListOfSomething<>(entityClass));
    }

    /**
     * Listのジェネリクス情報を保有するためのラッパー
     *
     * @param <X> Listの型
     */
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

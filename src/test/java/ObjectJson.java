import java.util.Objects;

public class ObjectJson {
    public final String word;
    public final Integer num;
    public final Boolean flag;

    public ObjectJson(String word, Integer num, Boolean flag) {
        this.word = word;
        this.num = num;
        this.flag = flag;
    }

    @Override
    public boolean equals(Object target) {
        if (this == target) return true;
        else if (!(target instanceof ObjectJson)) return false;
        else {
            ObjectJson targetJson = (ObjectJson) target;
            return Objects.equals(this.word, targetJson.word) &&
                    Objects.equals(this.num, targetJson.num) &&
                    Objects.equals(this.flag, targetJson.flag);
        }
    }

}

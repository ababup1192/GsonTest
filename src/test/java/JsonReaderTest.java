import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class JsonReaderTest {

    @Test
    public void testJsonObjectRead() {
        Optional<ObjectJson> objectJsonOpt = JsonReader.readGson("object.json", ObjectJson.class);
        objectJsonOpt.map(objectJson -> {
            assertThat(objectJson, is(new ObjectJson("word", 123, true)));
            return true;
        }).orElseGet(() -> {
            fail();
            return false;
        });
    }

}

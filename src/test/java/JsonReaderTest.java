import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class JsonReaderTest {

    @Test
    public void testReadJsonObject() {
        Optional<ObjectJson> objectJsonOpt = JsonReader.readJsonObject("object.json", ObjectJson.class);
        objectJsonOpt.map(objectJson -> {
            assertThat(objectJson, is(new ObjectJson("word", 123, true)));
            return true;
        }).orElseGet(() -> {
            fail();
            return false;
        });
    }

    @Test
    public void testReadJsonObjects() {
        Collection<ObjectJson> objectJsonList = JsonReader.readJsonObjects("objects.json", ObjectJson.class);

        assertThat(objectJsonList, is(Arrays.asList
                (
                        new ObjectJson("word", 123, true),
                        new ObjectJson("word2", 456, false),
                        new ObjectJson("word3", 789, true)
                )
        ));
    }

}

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class FileReaderTest {

    @Test
    public void testFileRead() throws Exception {
        FileReader.fileRead("Hello.txt").map(content -> {
                    assertThat(content, is(
                            "Hello\n" + "Foo\n" + "Bar\n" + "Hoge"
                            )
                    );
                    return true;
                }
        ).orElseGet(() -> {
                    fail();
                    return false;
                }
        );
    }
}

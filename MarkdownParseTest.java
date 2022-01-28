import static org.junit.Assert.*;
import org.junit.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MarkdownParseTest {
    @Test
    public void addition() {
        assertEquals(2, 1 + 1);
    }

    @Test
    public void TestTestFile() throws IOException{
        Path fileName = Path.of("test-file.md");
        String contents = Files.readString(fileName);
        List<String> links = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), links);
    }
}


import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.nio.file.Files;
import java.nio.file.Path;

class GuiMainTest {
    private FrameFixture app;

    @BeforeEach
    public void setUp() {
        app = new FrameFixture(new Main());
    }

    @AfterEach
    public void tearDown() {
        app.cleanUp();
    }
    @Test
    void testStartUp() throws InterruptedException {
        Thread.sleep(1);
    }
}

class FileMainTest {
    private String filenameIn;
    private String filenameOut = "src/test/output.jpg";
    @AfterEach
    public void clearFiles() {
        try {
            Files.delete(Path.of(filenameOut));
        } catch (IOException ignored) {}
    }
    @Test
    void testConvert() {
        filenameIn = "src/test/input.png";
        File f = new File(filenameIn);
        Assertions.assertTrue(f.exists());
        String format = "jpg";
        try {
            Converter.convert(filenameIn, format, filenameOut);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
    }
    @Test
    void testConvert2() {
        filenameIn = "src/test/impossible.png";
        String format = "jpg";
        try {
            Converter.convert(filenameIn, format, filenameOut);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertFalse(new File(filenameOut).exists());
    }
    @Test
    void testConvert3() {
        filenameIn = "src/test/input.png";
        String format = "impossible_format";
        filenameOut = filenameOut.replace("jpg", format);
        try {
            Converter.convert(filenameIn, format, filenameOut);
        } catch (Exception e) {
            Assertions.fail(e.getMessage());
        }
        Assertions.assertFalse(new File(filenameOut).exists());
    }
}
import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
        try {
            app.cleanUp();
            app.close();
        } catch (Exception ignored) {}
    }
    @Test
    void testStartUp() throws InterruptedException {
        Thread.sleep(1);
    }
}

class FileMainTest {
    private String filenameOut;
    @AfterEach
    public void clearFiles() {
        try {
            Files.delete(Path.of(filenameOut));
        } catch (IOException ignored) {}
    }
}
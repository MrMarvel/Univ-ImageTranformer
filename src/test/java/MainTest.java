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
        try {
            Main main = new Main();
            app = new FrameFixture(main);
        } catch (Exception e) {
            assert(false);
        }
        app.show();
    }

    @AfterEach
    public void tearDown() {
        try {
            app.close();
            app.cleanUp();
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
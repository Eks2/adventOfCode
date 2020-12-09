package year2020.day9;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day9Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2020/day9/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2020/day9/example");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day9.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH), 5);
        assertThat(actualCount).isEqualTo(127L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day9.processStringStreamPart1(Files.lines(INPUT_FILE_PATH), 25);
        assertThat(actualCount).isEqualTo(20874512L);
    }

    @Test
    void testExampleStep2() throws IOException {
        long actualCount = Day9.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH), 5, 127L);
        assertThat(actualCount).isEqualTo(62L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day9.processStringStreamPart2(Files.lines(INPUT_FILE_PATH), 25, 20874512L);
        assertThat(actualCount).isEqualTo(3012420L);
    }
}
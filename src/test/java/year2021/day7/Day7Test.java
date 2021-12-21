package year2021.day7;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day7Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2021/day7/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2021/day7/example1");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day7.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(37L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day7.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(323647L);
    }

    @Test
    void testExampleStep2() throws IOException {
        long actualCount = Day7.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(168L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day7.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(87640209L);
    }
}
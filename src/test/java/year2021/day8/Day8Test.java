package year2021.day8;

import org.junit.jupiter.api.Test;
import year2021.day7.Day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2021/day8/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2021/day8/example1");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day8.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(26L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day8.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(303L);
    }

    @Test
    void testExampleStep2() throws IOException {
        long actualCount = Day8.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(61229L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day8.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(961734L);
    }
}
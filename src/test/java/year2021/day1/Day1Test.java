package year2021.day1;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day1Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2021/day1/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2021/day1/example1");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day1.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(7L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day1.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(1692L);
    }

    @Test
    void testExampleStep2() throws IOException {
        long actualCount = Day1.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(5L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day1.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(1724L);
    }
}
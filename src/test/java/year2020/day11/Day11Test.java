package year2020.day11;

import org.junit.jupiter.api.Test;
import year2020.day10.Day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day11Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2020/day11/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2020/day11/example");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day11.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(37L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day11.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(2406L);
    }

    @Test
    void testExample1Step2() throws IOException {
        long actualCount = Day11.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(26L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day11.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(3454189699072L);
    }

}
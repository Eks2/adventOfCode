package year2020.day10;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day10Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2020/day10/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2020/day10/example");
    public static final Path EXAMPLE_1_FILE_PATH = Paths.get("src/test/java/year2020/day10/example1");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day10.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(220L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day10.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(1820L);
    }

    @Test
    void testExampleStep2() throws IOException {
        long actualCount = Day10.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(19208L);
    }

    @Test
    void testExample1Step2() throws IOException {
        long actualCount = Day10.processStringStreamPart2(Files.lines(EXAMPLE_1_FILE_PATH));
        assertThat(actualCount).isEqualTo(8L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day10.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(3454189699072L);
    }

}
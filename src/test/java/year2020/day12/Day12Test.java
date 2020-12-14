package year2020.day12;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day12Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2020/day12/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2020/day12/example");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day12.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(25L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day12.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(1687L);
    }

    @Test
    void testExample1Step2() throws IOException {
        long actualCount = Day12.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(286L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day12.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(20873L);
    }

}
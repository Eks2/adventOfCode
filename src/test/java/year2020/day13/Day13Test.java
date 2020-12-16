package year2020.day13;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day13Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2020/day13/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2020/day13/example");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day13.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(295L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day13.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(1835L);
    }

    @Test
    void testExample1Step2() throws IOException {
        long actualCount = Day13.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(1068781L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day13.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(247086664214628L);
    }

}
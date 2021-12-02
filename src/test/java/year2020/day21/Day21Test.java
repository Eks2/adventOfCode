package year2020.day21;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day21Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2020/day21/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2020/day21/example");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day21.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(5L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day21.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(2389L);
    }

    @Test
    void testExample1Step2() throws IOException {
        String actualCount = Day21.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo("mxmxvkd,sqjhc,fvjkl");
    }

    @Test
    void testInputStep2() throws IOException {
        String actualCount = Day21.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo("asdsda");
    }

}
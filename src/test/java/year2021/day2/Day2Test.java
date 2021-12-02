package year2021.day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day2Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2021/day2/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2021/day2/example1");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day2.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(150L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day2.processStringStreamPart1(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(1893605L);
    }

    @Test
    void testExampleStep2() throws IOException {
        long actualCount = Day2.processStringStreamPart2(Files.lines(EXAMPLE_FILE_PATH));
        assertThat(actualCount).isEqualTo(900L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day2.processStringStreamPart2(Files.lines(INPUT_FILE_PATH));
        assertThat(actualCount).isEqualTo(2120734350L);
    }


}
package year2021.day4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class Day4Test {

    public static final Path INPUT_FILE_PATH = Paths.get("src/test/java/year2021/day4/input");
    public static final Path EXAMPLE_FILE_PATH = Paths.get("src/test/java/year2021/day4/example1");

    @Test
    void testExampleStep1() throws IOException {
        long actualCount = Day4.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH), Comparator.comparing(Result::index));
        assertThat(actualCount).isEqualTo(4512L);
    }

    @Test
    void testInputStep1() throws IOException {
        long actualCount = Day4.processStringStreamPart1(Files.lines(INPUT_FILE_PATH), Comparator.comparing(Result::index));
        assertThat(actualCount).isEqualTo(16674L);
    }

    @Test
    void testExampleStep2() throws IOException {
        long actualCount = Day4.processStringStreamPart1(Files.lines(EXAMPLE_FILE_PATH), Comparator.comparing(Result::index).reversed());
        assertThat(actualCount).isEqualTo(1924L);
    }

    @Test
    void testInputStep2() throws IOException {
        long actualCount = Day4.processStringStreamPart1(Files.lines(INPUT_FILE_PATH), Comparator.comparing(Result::index).reversed());
        assertThat(actualCount).isEqualTo(7075L);
    }
}
package day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

class Day8Test {

    @Test
    void testExampleStep1() throws IOException {
        Path filePath = Paths.get("src/test/java/day8/example");
        long actualCount = Day8.processStringStreamPart1(Files.lines(filePath));
        assertThat(actualCount).isEqualTo(5L);
        assertThat(Day8.isProgramInfinite(Files.lines(filePath))).isTrue();
    }

    @Test
    void testInputStep1() throws IOException {
        Path filePath = Paths.get("src/test/java/day8/input");
        long actualCount = Day8.processStringStreamPart1(Files.lines(filePath));
        assertThat(actualCount).isEqualTo(1451L);
        assertThat(Day8.isProgramInfinite(Files.lines(filePath))).isTrue();
    }

    @Test
    void testExampleStep2() throws IOException {
        Path filePath = Paths.get("src/test/java/day8/example");
        long actualCount = Day8.processStringStreamPart2(Files.lines(filePath));
        assertThat(actualCount).isEqualTo(8L);
    }

    @Test
    void testInputStep2() throws IOException {
        Path filePath = Paths.get("src/test/java/day8/input");
        long actualCount = Day8.processStringStreamPart2(Files.lines(filePath));
        assertThat(actualCount).isEqualTo(1160L);
    }
}
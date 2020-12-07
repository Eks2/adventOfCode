package day2;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testPageExampleInput() {
        List<PasswordLine> passwordLines = List.of(
                PasswordLine.buildFrom("1-3 a: abcde"),
                PasswordLine.buildFrom("1-3 b: cdefg"),
                PasswordLine.buildFrom("2-9 c: ccccccccc")
        );
        long count = passwordLines.stream()
                .map(PasswordLine::isValidStep1)
                .filter(Boolean::booleanValue)
                .count();

        assertEquals(2, count);
    }

    @Test
    void testPageExampleStep1() throws IOException {
        Path filePath = Paths.get("src/test/java/day2/input");
        long validCount = Files.lines(filePath)
                .map(PasswordLine::buildFrom)
                .map(PasswordLine::isValidStep1)
                .filter(Boolean::booleanValue)
                .count();
        assertEquals(622, validCount);
    }

    @Test
    void testPageExampleStep2() throws IOException {
        Path filePath = Paths.get("src/test/java/day2/input");
        long validCount = Files.lines(filePath)
                .map(PasswordLine::buildFrom)
                .map(PasswordLine::isValidStep2)
                .filter(Boolean::booleanValue)
                .count();
        assertEquals(263, validCount);
    }
}
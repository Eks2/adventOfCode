package day7;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class Day7NodeTest {

    public static final List<String> STRINGS = List.of(
            "light red bags contain 1 bright white bag, 2 muted yellow bags.",
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
            "bright white bags contain 1 shiny gold bag.",
            "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
            "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
            "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
            "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
            "faded blue bags contain no other bags.",
            "dotted black bags contain no other bags.");

    @Test
    void testExampleStep1() {
        long actualCount = Day7Node.processStringStreamPart1("shiny gold", STRINGS.stream());
        assertThat(actualCount).isEqualTo(4L);
    }

    @Test
    void testInputStep1() throws IOException {
        Path filePath = Paths.get("src/test/java/day7/input");
        long actualCount = Day7Node.processStringStreamPart1("shiny gold", Files.lines(filePath));
        assertThat(actualCount).isEqualTo(278L);
    }

    @Test
    void testExampleStep2() {
        long actualCount = Day7Node.processStringStreamPart2("shiny gold", STRINGS.stream());
        assertThat(actualCount).isEqualTo(32L);
    }

    @Test
    void testInputStep2() throws IOException {
        Path filePath = Paths.get("src/test/java/day7/input");
        long actualCount = Day7Node.processStringStreamPart2("shiny gold", Files.lines(filePath));
        assertThat(actualCount).isEqualTo(45157L);
    }
}
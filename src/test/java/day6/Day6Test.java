package day6;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class Day6Test {
    @Test
    void testExampleStep1() {

        List<String> groups = List.of("abc", "abc", "abac", "aaaa", "b");
        Integer score = groups.stream()
                .map(String::chars)
                .map(intStream -> intStream.mapToObj(i -> (char) i))
                .map(characterStream -> characterStream.collect(Collectors.toSet()))
                .map(Set::size)
                .reduce(0, Integer::sum);
        assertEquals(11, score);
    }

    @Test
    void testInputStep1() throws IOException {
        String string = Files.readString(Paths.get("src/test/java/day6/input"));
        List<String> string1 = Arrays.asList(string.split("\n\n"));
        Integer score = getAnswerStrings().lines()
                .map(String::chars)
                .map(intStream -> intStream.mapToObj(i -> (char) i))
                .map(characterStream -> characterStream.collect(Collectors.toSet()))
                .map(Set::size)
                .reduce(0, Integer::sum);
        assertEquals(7283, score);
    }

    @Test
    void testInputStep2() throws IOException {
        String string = Files.readString(Paths.get("src/test/java/day6/input"));
        List<String> groups = Arrays.asList(string.split("\n\n"));
        Integer score = groups.stream()
                .map(s -> s.lines()
                        .map(this::getReverseSet)
                        .reduce(new HashSet<Character>(), (characters, characters2) -> {characters.addAll(characters2); return characters;})
                )
                .map(this::getReverseSet)
                .map(Set::size)
                .reduce(0, Integer::sum);
        assertEquals(3520, score);
    }

    private Set<Character> getFullSet() {
        return getSetOfString("abcdefghijklmnopqrstuvwxyz");
    }

    private Set<Character> getSetOfString(String string) {
        return string.chars().mapToObj(i -> (char) i).collect(Collectors.toSet());
    }

    private Set<Character> getReverseSet(String string) {
        Set<Character> fullSet = getFullSet();
        fullSet.removeAll(getSetOfString(string));
        return fullSet;
    }

    private Set<Character> getReverseSet(Set<Character> set) {
        Set<Character> fullSet = getFullSet();
        fullSet.removeAll(set);
        return fullSet;
    }

    private String getAnswerStrings() throws IOException {
        String string = Files.readString(Paths.get("src/test/java/day6/input"));
        string = string.replaceAll("(?<!\n)\n(?!\n)", "");
        string = string.replaceAll("\n\n", "\n");
        return string;
    }
}
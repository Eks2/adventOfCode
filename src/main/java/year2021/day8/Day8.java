package year2021.day8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Day8 {
    public static long processStringStreamPart1(Stream<String> lines) {
        Pattern p = Pattern.compile("\\| (.*)");
        return lines.map(p::matcher)
                .filter(Matcher::find)
                .map(matcher -> matcher.group(1))
                .map(s -> s.split(" "))
                .flatMap(Arrays::stream)
                .map(String::length)
                .filter(i -> i == 2 || i == 3 || i == 4 || i == 7)
                .count();
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        return lines.map(Line::buildFrom)
                .map(Line::getScore)
                .reduce(0L, Long::sum);
    }
}

record Line(Map<Integer, Set<Set<Character>>> allDigits, List<Set<Character>> output) {

    private static final Pattern PATTERN = Pattern.compile("(.*) \\| (.*)");
    private static final Map<Integer, Integer> KNOWN_INTEGERS = Map.of(
            1, 2,
            4, 4,
            7, 3,
            8, 7
    );

    static Line buildFrom(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (!matcher.find()) {
            throw new RuntimeException("Match not found");
        }
        Map<Integer, Set<Set<Character>>> allDigits = Arrays.stream(matcher.group(1).split(" "))
                .map(s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toUnmodifiableSet()))
                .collect(Collectors.groupingBy(Set::size, Collectors.toSet()));
        List<Set<Character>> output = Arrays.stream(matcher.group(2).split(" "))
                .map(s -> s.chars().mapToObj(c -> (char) c).collect(Collectors.toUnmodifiableSet()))
                .toList();
        return new Line(allDigits, output);
    }

    long getScore() {
        Map<Set<Character>, Integer> results = getResults();
        String decimalNumber = output.stream()
                .map(results::get)
                .map(String::valueOf)
                .collect(Collectors.joining());
        return Integer.parseInt(decimalNumber);
    }

    Map<Set<Character>, Integer> getResults() {
        Set<Character> one = findKnownInteger(1);
        Set<Character> four = findKnownInteger(4);
        Set<Character> seven = findKnownInteger(7);
        Set<Character> eight = findKnownInteger(8);
        Set<Character> three = findThree(one);
        Set<Character> nine = findNine(three);
        Set<Character> six = findSix(one);
        Set<Character> zero = findZero(nine, six);
        Set<Character> two = findTwo(three, nine);
        Set<Character> five = findFive(three, two);
        return Map.of(
                zero, 0,
                one, 1,
                two, 2,
                three, 3,
                four, 4,
                five, 5,
                six, 6,
                seven, 7,
                eight, 8,
                nine, 9
        );
    }

    Set<Character> findKnownInteger(int value) {
        return allDigits.get(KNOWN_INTEGERS.get(value))
                .stream()
                .findFirst()
                .get();
    }

    Set<Character> findThree(Set<Character> one) {
        return allDigits.get(5)
                .stream()
                .filter(strings -> strings.containsAll(one))
                .findFirst()
                .get();
    }

    Set<Character> findNine(Set<Character> three) {
        return allDigits.get(6)
                .stream()
                .filter(strings -> strings.containsAll(three))
                .findFirst()
                .get();
    }

    Set<Character> findSix(Set<Character> one) {
        return allDigits.get(6)
                .stream()
                .filter(strings -> !strings.containsAll(one))
                .findFirst()
                .get();
    }

    Set<Character> findZero(Set<Character> nine, Set<Character> six) {
        return allDigits.get(6)
                .stream()
                .filter(not(nine::equals))
                .filter(not(six::equals))
                .findFirst()
                .get();
    }

    Set<Character> findTwo(Set<Character> three, Set<Character> nine) {
        return allDigits.get(5)
                .stream()
                .filter(not(three::equals))
                .filter(strings -> {
                    HashSet<Character> nineCopy = new HashSet<>(nine);
                    nineCopy.retainAll(strings);
                    return nineCopy.size() == 4;
                })
                .findFirst()
                .get();

    }

    Set<Character> findFive(Set<Character> three, Set<Character> two) {
        return allDigits.get(5)
                .stream()
                .filter(not(three::equals))
                .filter(not(two::equals))
                .findFirst()
                .get();

    }
}
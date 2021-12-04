package year2021.day4;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Day4 {
    public static long processStringStreamPart1(Stream<String> lines, Comparator<Result> comparing) {
        String wholeFile = lines.collect(Collectors.joining("\n"));
        Pattern p1 = Pattern.compile("(\\A|,)(\\d+)");
        List<Integer> integers = p1.matcher(wholeFile)
                .results()
                .map(matchResult -> matchResult.group(2))
                .map(Integer::parseInt)
                .toList();
        Pattern p2 = Pattern.compile("(?<=\\n\\n).+?(?=(\\n\\n)|\\Z)", Pattern.DOTALL);
        return p2.matcher(wholeFile)
                .results()
                .map(MatchResult::group)
                .map(Card::buildFrom)
                .map(card -> card.problem1(integers))
                .filter(Optional::isPresent)
                .flatMap(Optional::stream)
                .sorted(comparing)
                .findFirst()
                .get()
                .score();
    }
}

record Result(int score, int index) {}

record Card(Map<Integer, Position> squares) {
    Optional<Result> problem1(List<Integer> values) {
        return IntStream.range(0, values.size())
                .filter(i -> isBingo(getCompoundIntegers(values, i)))
                .mapToObj(i -> new Result(getSumOfUnmatchedSquares(getCompoundIntegers(values, i).stream().collect(Collectors.toUnmodifiableSet())) * getCompoundIntegers(values, i).get(i-1), i))
                .findFirst();
    }

    private Integer getSumOfUnmatchedSquares(Set<Integer> matchedSquares) {
        return squares.keySet()
                .stream()
                .filter(not(matchedSquares::contains))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private boolean isBingo(List<Integer> activatedValues) {
        Set<Position> positions = activatedValues.stream()
                .filter(squares::containsKey)
                .map(squares::get)
                .collect(Collectors.toUnmodifiableSet());
        return isBingo(positions);
    }

    private boolean isBingo(Set<Position> activatedPositions) {
        return activatedPositions.stream()
                .map(this::getPotentialBingoLines)
                .flatMap(Collection::stream)
                .anyMatch(activatedPositions::containsAll);
    }

    private Set<Set<Position>> getPotentialBingoLines(Position position) {
//        Set<Position> diagonal1 = Set.of(new Position(0, 0), new Position(1, 1), new Position(2, 2), new Position(3, 3), new Position(4, 4));
//        Set<Position> diagonal2 = Set.of(new Position(0, 4), new Position(1, 3), new Position(2, 2), new Position(3, 1), new Position(4, 0));

        Set<Set<Position>> sets = new HashSet<>();
        sets.add(IntStream.range(0, 5).mapToObj(i -> new Position(i, position.y())).collect(Collectors.toUnmodifiableSet()));
        sets.add(IntStream.range(0, 5).mapToObj(i -> new Position(position.x(), i)).collect(Collectors.toUnmodifiableSet()));

//        if (diagonal1.contains(position)) {
//            sets.add(diagonal1);
//        }
//
//        if (diagonal2.contains(position)) {
//            sets.add(diagonal2);
//        }

        return sets;
    }

    static Card buildFrom(String string) {
        Pattern p1 = Pattern.compile("\\d+");
        List<Integer> integers = p1.matcher(string)
                .results()
                .map(MatchResult::group)
                .map(Integer::parseInt)
                .toList();

        Map<Integer, Position> squares = IntStream.range(0, integers.size())
                .boxed()
                .collect(Collectors.toMap(integers::get, Position::buildFrom));
        return new Card(squares);
    }

    private List<Integer> getCompoundIntegers(List<Integer> list, int i) {
        return IntStream.range(0, i)
                .map(list::get)
                .boxed()
                .toList();
    }
}

record Position(int x, int y) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    static Position buildFrom(int i) {
        return new Position(i % 5, i / 5);
    }
}

package year2020.day11;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day11 {
    public static long processStringStreamPart1(Stream<String> lines) {
        Seats seats = getSeats(lines);
        while (seats.isNotStabilized()) {
            seats.stepPart1();
        }
        return seats.getNumberOfOccupiedSeats();
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        Seats seats = getSeats(lines);
        while (seats.isNotStabilized()) {
            seats.stepPart2();
        }
        return seats.getNumberOfOccupiedSeats();
    }

    private static Seats getSeats(Stream<String> lines) {
        List<String> stringList = lines.collect(Collectors.toUnmodifiableList());
        int width = stringList.get(0).length();
        int height = stringList.size();
        Map<Position, Character> positionCharacterMap = IntStream.range(0, height)
                .mapToObj(y -> IntStream.range(0, width)
                        .mapToObj(x -> new Position(x, y))
                )
                .flatMap(stream -> stream)
                .collect(Collectors.toMap(Function.identity(), position -> stringList.get(position.getY()).charAt(position.getX())));
        return new Seats(positionCharacterMap, width, height);
    }
}

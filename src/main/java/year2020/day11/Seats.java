package year2020.day11;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class Seats {
    Map<Position, Character> positionCharacterMap;
    Map<Position, Character> oldPositionCharacterMap;
    int width;
    int height;

    public Seats(Map<Position, Character> positionCharacterMap, int width, int height) {
        this.positionCharacterMap = positionCharacterMap;
        this.width = width;
        this.height = height;
    }

    public void stepPart1() {
        printTable();
        System.out.println("");
        oldPositionCharacterMap = positionCharacterMap;
        positionCharacterMap = positionCharacterMap.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), this::getNewStatusPart1));
    }

    public void stepPart2() {
        printTable();
        System.out.println("");
        oldPositionCharacterMap = positionCharacterMap;
        positionCharacterMap = positionCharacterMap.keySet()
                .stream()
                .collect(Collectors.toMap(Function.identity(), this::getNewStatusPart2));
    }

    public boolean isNotStabilized() {
        return !positionCharacterMap.equals(oldPositionCharacterMap);
    }

    public char getNewStatusPart1(Position position) {
        List<Position> adjacentSeats = getAdjacentSeats(position);
        long numberOfAdjacentOccupiedSeats = adjacentSeats.stream()
                .map(positionCharacterMap::get)
                .filter(character -> character.equals('#'))
                .count();
        Character positionStatus = positionCharacterMap.get(position);
        if (positionStatus.equals('L') && numberOfAdjacentOccupiedSeats == 0) {
            return '#';
        } else if (positionStatus.equals('#') && numberOfAdjacentOccupiedSeats >= 4) {
            return 'L';
        } else {
            return positionStatus;
        }
    }

    public char getNewStatusPart2(Position position) {
        List<Position> adjacentSeats = getVisibleSeats(position);
        long numberOfAdjacentOccupiedSeats = adjacentSeats.stream()
                .map(positionCharacterMap::get)
                .filter(character -> character.equals('#'))
                .count();
        Character positionStatus = positionCharacterMap.get(position);
        if (positionStatus.equals('L') && numberOfAdjacentOccupiedSeats == 0) {
            return '#';
        } else if (positionStatus.equals('#') && numberOfAdjacentOccupiedSeats >= 5) {
            return 'L';
        } else {
            return positionStatus;
        }
    }

    public long getNumberOfOccupiedSeats() {
        return positionCharacterMap.values()
                .stream()
                .filter(character -> character.equals('#'))
                .count();
    }

    private List<Position> getAdjacentSeats(Position position) {
        return getAdjacentPositionStream(position)
                .filter(adjacentPosition -> !positionCharacterMap.get(adjacentPosition).equals('.'))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Position> getVisibleSeats(Position position) {
        return getAdjacentPositionStream(position)
                .map(pos -> new Position(pos.getX() - position.getX(), pos.getY() - position.getY()))
                .flatMap(direction -> Stream.iterate(
                            position,
                            pos -> pos.isInBounds(width, height),
                            pos -> pos.add(direction))
                        .filter(pos -> !positionCharacterMap.get(pos).equals('.'))
                        .findFirst()
                        .stream()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    private Stream<Position> getAdjacentPositionStream(Position position) {
        return IntStream.range(max(position.getX() - 1, 0), min(position.getX() + 2, width))
                .mapToObj(x -> IntStream.range(max(position.getY() - 1, 0), min(position.getY() + 2, height)).mapToObj(y -> new Position(x, y)))
                .flatMap(stream -> stream)
                .filter(adjacentPosition -> !adjacentPosition.equals(position));
    }

    private void printTable() {
        IntStream.range(0, width)
                .mapToObj(
                        x -> IntStream.range(0, height)
                                .mapToObj(y -> new Position(y, x))
                                .map(positionCharacterMap::get)
                                .collect(Collector.of(
                                        StringBuilder::new,
                                        StringBuilder::append,
                                        StringBuilder::append,
                                        StringBuilder::toString)
                                )
                )
                .forEach(System.out::println);
    }
}

package year2020.day12;

import java.util.stream.Stream;

public class Day12 {
    public static long processStringStreamPart1(Stream<String> lines) {

        Ship ship = new Ship(0, 0, 90);
        ship.processInstructions(lines);
        return ship.getManhattanDistance(0, 0);
    }

    public static long processStringStreamPart2(Stream<String> lines) {

        ShipWaypoint ship = new ShipWaypoint(0, 0, 10, 1);
        ship.processInstructions(lines);
        return ship.getManhattanDistance(0, 0);
    }
}

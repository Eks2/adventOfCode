package year2020.day12;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Math.abs;

@AllArgsConstructor
public class ShipWaypoint {

    static Pattern pattern = Pattern.compile("(.)(\\d+)");

    long x;
    long y;
    long waypointX;
    long waypointY;

    public void processInstructions(Stream<String> lines) {
        lines.map(pattern::matcher)
                .filter(Matcher::matches)
                .forEach(matcher -> processInstruction(matcher.group(1).charAt(0), Integer.parseInt(matcher.group(2))));

    }

    private void processInstruction(Character c, int value) {
        switch (c) {
            case 'N':
                waypointY += value;
                return;
            case 'E':
                waypointX += value;
                return;
            case 'S':
                waypointY -= value;
                return;
            case 'W':
                waypointX -= value;
                return;
            case 'R':
                IntStream.range(0, value/90).forEach(i -> turnClockwise90());
                return;
            case 'L':
                IntStream.range(0, value/90).forEach(i -> turnAntiClockwise90());
                return;
            case 'F':
                IntStream.range(0, value).forEach(i -> {
                    x += waypointX;
                    y += waypointY;
                });
                return;
            default:
                throw new RuntimeException("Can't get here");
        }
    }

    private void turnClockwise90() {
        long tempX = waypointX;
        waypointX = waypointY;
        waypointY = -tempX;
    }

    void turnAntiClockwise90() {
        long tempX = waypointX;
        waypointX = -waypointY;
        waypointY = tempX;
    }

    long getManhattanDistance(int x0, int y0) {
        return abs(x - x0) + abs(y - y0);
    }
}

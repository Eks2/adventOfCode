package year2020.day12;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Math.abs;

@AllArgsConstructor
public class Ship {

    static Map<Integer, Character> COMPASS_DIRECTION_MAP
            = Map.of(0, 'N', 90, 'E', 180, 'S', 270, 'W', -90, 'W', -180, 'S', -270, 'E');
    static Pattern pattern = Pattern.compile("(.)(\\d+)");

    long x;
    long y;
    int direction;

    public void processInstructions(Stream<String> lines) {
        lines.map(pattern::matcher)
                .filter(Matcher::matches)
                .forEach(matcher -> processInstruction(matcher.group(1).charAt(0), Integer.parseInt(matcher.group(2))));

    }

    private void processInstruction(Character c, int value) {
        switch (c) {
            case 'N':
                y += value;
                return;
            case 'E':
                x += value;
                return;
            case 'S':
                y -= value;
                return;
            case 'W':
                x -= value;
                return;
            case 'R':
                direction = (direction + value) % 360;
                return;
            case 'L':
                direction = (direction - value) % 360;
                return;
            case 'F':
                processInstruction(getCompassDirection(), value);
                return;
            default:
                throw new RuntimeException("Can't get here");
        }
    }

    char getCompassDirection() {
        return COMPASS_DIRECTION_MAP.get(direction);
    }

    long getManhattanDistance(int x0, int y0) {
        return abs(x - x0) + abs(y - y0);
    }
}

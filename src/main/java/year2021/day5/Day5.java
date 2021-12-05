package year2021.day5;

import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day5 {
    public static long processStringStreamPart1(Stream<String> lines) {
        return lines.map(Line::buildFrom)
                .filter(Line::isHorizontalOrVertical)
                .flatMap(Line::getMiddlePoints)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .filter(aLong -> aLong > 1)
                .count();
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        return lines.map(Line::buildFrom)
                .filter(Line::isStraight)
                .flatMap(Line::getMiddlePoints)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(aLong -> aLong.getValue() > 1)
                .count();
    }
}

record Point(int x, int y) {

    boolean isAtSameX(Point point) {
        return x == point.x();
    }

    boolean isAtSameY(Point point) {
        return y == point.y();
    }
}

record Line(Point point1, Point point2) {

    private static final Pattern PATTERN = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");

    static Line buildFrom(String string) {
        Matcher matcher = PATTERN.matcher(string);
        if (!matcher.find()) {
            throw new RuntimeException("Could not find match");
        }

        Point point1 = new Point(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        Point point2 = new Point(Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));
        Point point3 = point1.x() < point2.x() ? point1 : point2;
        Point point4 = point1.x() < point2.x() ? point2 : point1;
        return new Line(point3, point4);
    }

    boolean isHorizontalOrVertical() {
        return arePointsAtSameX() || arePointsAtSameY();
    }

    boolean isStraight() {
        return arePointsAtSameX() || arePointsAtSameY() || arePointsOnDiagonal();
    }

    private boolean arePointsOnDiagonal() {
        return Math.abs(point1.x() - point2.x()) == Math.abs(point1.y() - point2.y());
    }

    private boolean arePointsAtSameY() {
        return point1.isAtSameY(point2);
    }

    private boolean arePointsAtSameX() {
        return point1.isAtSameX(point2);
    }

    Stream<Point> getMiddlePoints() {
        if (arePointsAtSameX()) {
            return IntStream.rangeClosed(getLowerY(), getUpperY())
                    .mapToObj(i -> new Point(point1.x(), i));
        }
        if (arePointsAtSameY()) {
            return IntStream.rangeClosed(getLowerX(), getUpperX())
                    .mapToObj(i -> new Point(i, point1.y()));
        }
        if (arePointsOnDiagonal()) {
            double theta = Math.atan((point2().y() - point1().y())/ (double) (point2.x() - point1.x()));
            double r = Math.sqrt(2);
            return IntStream.rangeClosed(0, getUpperX() - getLowerX())
                    .mapToObj(i -> new Point((int) Math.round(point1.x() + i*r*Math.cos(theta)), (int) Math.round(point1.y() + i*r*Math.sin(theta))));
        }
        return Stream.of();
    }

    int getLowerX() {
        return Math.min(point1.x(), point2.x());
    }

    int getUpperX() {
        return Math.max(point1.x(), point2.x());
    }

    int getLowerY() {
        return Math.min(point1.y(), point2.y());
    }

    int getUpperY() {
        return Math.max(point1.y(), point2.y());
    }
}
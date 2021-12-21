package year2021.day7;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Day7 {
    public static long processStringStreamPart1(Stream<String> lines) {
        return getFuelScore(lines, Day7::getMedian);
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        return getFuelScore(lines, Day7::getMean);
    }

    private static Long getFuelScore(Stream<String> lines, Function<List<Integer>, Long> method) {
        List<Integer> integers = lines.map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .map(Integer::parseInt)
                .sorted()
                .toList();
        Long value = method.apply(integers);
        return integers.stream()
                .map(integer -> integer - value)
                .map(Math::abs)
                .map(i -> Math.round(0.5*i*(i + 1)))
                .reduce(0L, Long::sum);
    }

    private static long getMedian(List<Integer> integers) {
        double median;
        if (integers.size() % 2 == 0)
            median = ((double) integers.get(integers.size() / 2) + (double) integers.get(integers.size() / 2 - 1)) / 2;
        else {
            median = (double) integers.get(integers.size() / 2);
        }

        return Math.round(median);
    }

    private static long getMean(List<Integer> integers) {
        return Math.round(Math.floor((double) integers.stream().reduce(0, Integer::sum) / (double) integers.size()));
    }

}

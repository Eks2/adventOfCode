package year2020.day10;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day10 {
    public static long processStringStreamPart1(Stream<String> lines) {
        List<Long> sortedList = getSortedLongList(lines);
        List<Long> diffs = IntStream.range(0, sortedList.size() - 1)
                .mapToObj(i -> sortedList.get(i + 1) - sortedList.get(i))
                .collect(Collectors.toUnmodifiableList());
        Map<Long, Long> countLongs = diffs.stream().collect(Collectors.groupingBy(p -> p,
                Collectors.counting()));
        return countLongs.get(1L) * countLongs.get(3L);
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        List<Long> sortedList = getSortedLongList(lines);

        Map<Long, Long> lastThreeSums = new HashMap<>();
        lastThreeSums.put(0L, 1L);

        for (int i = 1; i < sortedList.size(); i++) {
            Long currentAdapter = sortedList.get(i);
            long threeMapSum = lastThreeSums
                    .keySet()
                    .stream()
                    .filter(aLong -> aLong >= currentAdapter - 3)
                    .mapToLong(lastThreeSums::get)
                    .sum();
            if (lastThreeSums.size() == 3) {
                OptionalLong min = lastThreeSums.keySet()
                        .stream()
                        .mapToLong(Long::valueOf)
                        .min();
                lastThreeSums.remove(min.getAsLong());
            }
            lastThreeSums.put(currentAdapter, threeMapSum);
        }
        return lastThreeSums.values().stream().mapToLong(Long::valueOf).max().getAsLong();
    }

    private static List<Long> getSortedLongList(Stream<String> lines) {
        List<Long> collect = Stream.concat(Stream.of(0L), lines.map(Long::parseLong).sorted())
                .collect(Collectors.toUnmodifiableList());
        OptionalLong max = collect.stream().mapToLong(Long::valueOf).max();
        return Stream.concat(collect.stream(), Stream.of(max.getAsLong() + 3L)).collect(Collectors.toUnmodifiableList());
    }
}

package year2021.day1;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {
    public static long processStringStreamPart1(Stream<String> lines) {
        List<Long> longs = lines.map(Long::valueOf).toList();
        return IntStream.range(0, longs.size() - 1)
                .mapToObj(i -> new Pair(longs.get(i), longs.get(i + 1)))
                .map(Pair::diff)
                .filter(aLong -> aLong < 0)
                .count();

    }

    public static long processStringStreamPart2(Stream<String> lines) {
        List<Long> longs = lines.map(Long::valueOf).toList();
        return IntStream.range(0, longs.size() - 3)
                .mapToObj(i -> new PairOfTriplets(
                        new Triplet(longs.get(i), longs.get(i + 1), longs.get(i + 2)),
                        new Triplet(longs.get(i + 1), longs.get(i + 2), longs.get(i + 3))))
                .map(PairOfTriplets::diff)
                .filter(aLong -> aLong < 0)
                .count();

    }
}

record Pair(long first, long second) {
    long diff() {
        return first - second;
    }
}

record Triplet(long first, long second, long third) {
    long sum() {
        return first + second + third;
    }
}

record PairOfTriplets(Triplet first, Triplet second) {
    long diff() {
        return first.sum() - second.sum();
    }
}
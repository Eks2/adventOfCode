package year2021.day6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day6 {

    public static long processStringStreamPart1(Stream<String> lines) {
        return getCount(lines, 80);
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        return getCount(lines, 256);
    }

    private static long getCount(Stream<String> lines, int days) {
        Fishes fishes = new Fishes();
        lines.map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .map(Integer::parseInt)
                .forEach(fishes::addFish);

        IntStream.range(0, days).forEach(i -> fishes.tickDay());

        return fishes.sum();
    }
}

record Fishes() {
    private static final int FISH_LIFE = 9;

    private static final List<Long> FISH_LIST = new ArrayList<>(Collections.nCopies(FISH_LIFE, 0L));

    void addFish(int i) {
        FISH_LIST.set(i, FISH_LIST.get(i) + 1);
    }

    void tickDay() {
        Collections.rotate(FISH_LIST, -1);
        FISH_LIST.set(6, FISH_LIST.get(FISH_LIFE - 1) + FISH_LIST.get(6));
    }

    long sum() {
        return FISH_LIST.stream().reduce(0L, Long::sum);
    }
}
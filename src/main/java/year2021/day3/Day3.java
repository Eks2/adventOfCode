package year2021.day3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day3 {
    public static long processStringStreamPart1(Stream<String> lines, int numberOfBits) {
        List<BitColumn> bitColumns = IntStream.range(0, numberOfBits)
                .mapToObj(i -> new BitColumn(new ArrayList<>()))
                .toList();
        return lines.reduce(new Columns(bitColumns), Columns::add, Columns::combine)
                .day1Calculation();
    }

    public static long processStringStreamPart2(Stream<String> lines, int numberOfBits) {
        List<BitColumn> bitColumns = IntStream.range(0, numberOfBits)
                .mapToObj(i -> new BitColumn(new ArrayList<>()))
                .toList();
        return lines.reduce(new Columns(bitColumns), Columns::add, Columns::combine)
                .day2Calculation();
    }
}

record BitColumn(List<Character> bits) {
    void add(char c) {
        bits.add(c);
    }

    char getGammaRate() {
        return getMostCommonBit('0', getAllBitIndexes());
    }

    char getEpsilonRate() {
        return getMostCommonBit('1', getAllBitIndexes()) == '1' ? '0' : '1';
    }

    char getMostCommonBit(char defaultBitIfDraw, Set<Integer> bitsToConsider) {
        return IntStream.range(0, bits.size())
                .filter(bitsToConsider::contains)
                .mapToObj(bits::get)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.<Character, Long>comparingByValue().thenComparing((entry1, entry2) -> entry1.getKey().equals(defaultBitIfDraw) ? Integer.MAX_VALUE : Integer.MIN_VALUE))
                .get()
                .getKey();
    }

    Set<Integer> getIndexesWithCommonBit(char defaultBitIfDraw, Set<Integer> bitsToConsider) {
        char mostCommonBit = getMostCommonBit(defaultBitIfDraw, bitsToConsider);
        return bitsToConsider.stream()
                .filter(i -> bits.get(i) == mostCommonBit)
                .collect(Collectors.toUnmodifiableSet());
    }

    Set<Integer> getIndexesWithCommonBitC02(char defaultBitIfDraw, Set<Integer> bitsToConsider) {
        char mostCommonBit = getMostCommonBit(defaultBitIfDraw, bitsToConsider) == '1' ? '0' : '1';
        return bitsToConsider.stream()
                .filter(i -> bits.get(i) == mostCommonBit)
                .collect(Collectors.toUnmodifiableSet());
    }

    Set<Integer> getAllBitIndexes() {
        return IntStream.range(0, bits.size())
                .boxed()
                .collect(Collectors.toUnmodifiableSet());
    }
}

record Columns(List<BitColumn> bitColumns) {
    long day1Calculation() {
        return getRate(BitColumn::getGammaRate) * getRate(BitColumn::getEpsilonRate);
    }

    long day2Calculation() {
        return getC02ScrubberRating() * getOxygenGeneratorRating();
    }

    long getOxygenGeneratorRating() {
        Set<Integer> currentIndexes = null;
        for(BitColumn bitColumn : bitColumns) {
            if (currentIndexes == null) {
                currentIndexes = bitColumn.getAllBitIndexes();
            }
            currentIndexes = bitColumn.getIndexesWithCommonBit('1', currentIndexes);
            if (currentIndexes.size() == 1) {
                return Integer.parseInt(getBinaryNumberAtIndex(currentIndexes.stream().findFirst().get()), 2);
            }
        }
        return 0L;
    }

    long getC02ScrubberRating() {
        Set<Integer> currentIndexes = null;
        for(BitColumn bitColumn : bitColumns) {
            if (currentIndexes == null) {
                currentIndexes = bitColumn.getAllBitIndexes();
            }
            currentIndexes = bitColumn.getIndexesWithCommonBitC02('1', currentIndexes);
            if (currentIndexes.size() == 1) {
                return Integer.parseInt(getBinaryNumberAtIndex(currentIndexes.stream().findFirst().get()), 2);
            }
        }
        return 0L;
    }

    long getRate(Function<BitColumn, Character> getEpsilonRate) {
        return Integer.parseInt(bitColumns.stream().map(getEpsilonRate).map(String::valueOf).collect(Collectors.joining()), 2);
    }

    Columns combine(Columns columns) {
        List<BitColumn> collect = Stream.concat(bitColumns.stream(), columns.bitColumns.stream()).collect(Collectors.toList());
        return new Columns(collect);
    }

    Columns add(String string) {
        IntStream.range(0, string.length()).forEach(i -> bitColumns.get(i).add(string.charAt(i)));
        return this;
    }

    String getBinaryNumberAtIndex(int index) {
        return bitColumns.stream()
                .map(BitColumn::bits)
                .map(bits -> bits.get(index))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}


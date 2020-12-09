package year2020.day9;

import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day9 {
    public static long processStringStreamPart1(Stream<String> lines, int preamble) {
        XmasNumberCollection xmasNumberCollection = getXmasNumberCollection(lines, preamble);
        Optional<XmasNumber> maybeXmasNumber = xmasNumberCollection.getFirstInvalidXmasNumber();
        return maybeXmasNumber.get().getValue();
    }

    public static long processStringStreamPart2(Stream<String> lines, int preamble, long part1Sum) {
        XmasNumberCollection xmasNumberCollection = getXmasNumberCollection(lines, preamble);
        Optional<Deque<XmasNumber>> xmasNumberSet = xmasNumberCollection.getContiguousNumbers(part1Sum);
        return xmasNumberSet.get().stream()
                .map(XmasNumber::getValue)
                .mapToLong(Long::longValue)
                .max().getAsLong() + xmasNumberSet.get().stream()
                .map(XmasNumber::getValue)
                .mapToLong(Long::longValue)
                .min().getAsLong();
    }

    private static XmasNumberCollection getXmasNumberCollection(Stream<String> lines, int preamble) {
        List<String> stringList = lines.collect(Collectors.toUnmodifiableList());
        List<XmasNumber> xmasNumberList = IntStream.range(0, stringList.size())
                .mapToObj(i -> new XmasNumber(i, Long.parseLong(stringList.get(i))))
                .collect(Collectors.toUnmodifiableList());
        return new XmasNumberCollection(xmasNumberList, preamble);
    }
}

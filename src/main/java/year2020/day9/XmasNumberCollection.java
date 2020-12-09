package year2020.day9;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@AllArgsConstructor
@Value
public class XmasNumberCollection {
    List<XmasNumber> xmasNumberList;
    int preamble;

    public Optional<XmasNumber> getFirstInvalidXmasNumber() {
        return xmasNumberList.stream()
                .filter(this::isInvalid)
                .findFirst();
    }

    public Optional<Deque<XmasNumber>> getContiguousNumbers(long part1Sum) {
        Deque<XmasNumber> xmasNumberDeque = new ArrayDeque<>();
        return xmasNumberList.stream()
                .peek(xmasNumberDeque::addFirst)
                .flatMap(xmasNumber -> {
                    Iterator<XmasNumber> iterator = xmasNumberDeque.descendingIterator();
                    return Stream.generate(() -> null)
                        .takeWhile(x -> iterator.hasNext() && calculateSumOfXmasNumbers(xmasNumberDeque) > part1Sum)
                        .map(n -> iterator.next());
                })
                .map(xmasNumber -> calculateSumOfXmasNumbers(xmasNumberDeque))
                .peek(aLong -> {
                    if (aLong > part1Sum) {
                        xmasNumberDeque.removeLast();
                    }
                })
                .map(xmasNumber -> calculateSumOfXmasNumbers(xmasNumberDeque))
                .filter(aLong -> aLong == part1Sum)
                .map(aLong -> xmasNumberDeque)
                .findAny();

    }

    private boolean isInvalid(XmasNumber xmasNumber) {
        if (isInFirstPreamble(xmasNumber)) {
            return false;
        }

        Set<Long> valueSet = IntStream.range(xmasNumber.getLineNumber() - preamble, xmasNumber.getLineNumber())
                .mapToObj(xmasNumberList::get)
                .map(XmasNumber::getValue)
                .collect(Collectors.toSet());

        return valueSet.stream()
                .map(value -> xmasNumber.getValue() - value)
                .noneMatch(valueSet::contains);
    }

    private boolean isInFirstPreamble(XmasNumber xmasNumber) {
        return (xmasNumber.getLineNumber() - preamble) < 0;
    }

    private long calculateSumOfXmasNumbers(Deque<XmasNumber> xmasNumbers) {
        return xmasNumbers.stream()
                .map(XmasNumber::getValue)
                .mapToLong(Long::longValue)
                .sum();
    }
}

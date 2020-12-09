package year2020.day5;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day5Test {
    @Test
    void testExampleStep1() {
        SeatLine seatLine = SeatLine.buildFrom("FBFBBFFRLR");
        assertEquals(357, seatLine.getSeatId());
    }

    @Test
    void testInputStep1() throws IOException {
        Path filePath = Paths.get("src/test/java/year2020/day5/input");
        int max = Files.lines(filePath)
                .map(SeatLine::buildFrom)
                .map(SeatLine::getSeatId)
                .max(Integer::compareTo).get();
        assertEquals(888, max);
    }

    @Test
    void testInputStep2() throws IOException {
        Path filePath = Paths.get("src/test/java/year2020/day5/input");
        Set<Integer> set = Files.lines(filePath)
                .map(SeatLine::buildFrom)
                .map(SeatLine::getSeatId)
                .collect(Collectors.toSet());

        Integer min = Collections.min(set);
        Integer max = Collections.max(set);

        Set<Integer> fullSet = IntStream.range(min, max).boxed().collect(Collectors.toSet());

        set.forEach(fullSet::remove);
        assertEquals(Set.of(522), fullSet);
    }

}
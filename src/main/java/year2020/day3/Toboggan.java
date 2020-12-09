package year2020.day3;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
public class Toboggan {
    int right;
    int down;
    long surf(List<MapLine> mapLineList) {
        return IntStream.range(0, mapLineList.size())
                .filter(i -> (i % down) == 0)
                .filter(i -> mapLineList.get(i).isTreeAt(right*i/down))
                .count();
    }
}

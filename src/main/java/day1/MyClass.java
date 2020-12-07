package day1;

import java.util.List;
import java.util.Optional;

public class MyClass {

    public Optional<List<Integer>> findPairSumProduct(Integer sumInteger, List<Integer> inputList) {
        return inputList.stream()
                .map(value -> sumInteger - value)
                .filter(inputList::contains)
                .map(value -> List.of(value, (sumInteger - value)))
                .findFirst();
    }

    public Optional<List<Integer>> findPairTripletProduct(Integer sumInteger, List<Integer> inputList) {
        return inputList.stream()
                .map(value -> findPairSumProduct(sumInteger - value, inputList)
                        .map(integers -> List.of(integers.get(0), integers.get(1), value))
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny();
    }
}

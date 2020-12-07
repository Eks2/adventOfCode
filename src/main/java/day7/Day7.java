package day7;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7 {
    public static long processStringStreamPart1(String keyString, Stream<String> lines) {
        Map<String, ColouredBag> bagMap = lines.map(ColouredBag::buildFrom)
                .collect(Collectors.toUnmodifiableMap(ColouredBag::getColour, Function.identity()));

        return bagMap.values()
                .stream()
                .map(colouredBag -> containsBag(keyString, colouredBag, bagMap))
                .filter(aBoolean -> aBoolean)
                .count();
    }

    public static long processStringStreamPart2(String keyString, Stream<String> lines) {
        Map<String, ColouredBag> bagMap = lines.map(ColouredBag::buildFrom)
                .collect(Collectors.toUnmodifiableMap(ColouredBag::getColour, Function.identity()));

        ColouredBag keyBag = bagMap.get(keyString);
        return getChildBags(keyBag, bagMap).count();
    }

    private static boolean containsBag(String keyString, ColouredBag keyBag, Map<String, ColouredBag> bagMap) {
        return getChildBags(keyBag, bagMap)
                .map(ColouredBag::getColour)
                .anyMatch(string -> string.equals(keyString));
    }

    public static Stream<ColouredBag> getChildBags(ColouredBag bag, Map<String, ColouredBag> bagMap) {
        return bag.getChildBagToQuantityMap()
                .keySet()
                .stream()
                .flatMap(string ->
                        Stream.concat(
                        Stream.generate(() -> bagMap.get(string)).limit(bag.getChildBagToQuantityMap().get(string)),
                        getChildBags(bagMap.get(string), bagMap))
                );
    }
}

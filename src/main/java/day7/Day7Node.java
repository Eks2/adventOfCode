package day7;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day7Node {
    public static long processStringStreamPart1(String keyString, Stream<String> lines) {
        Map<String, ColouredBagNode> bagNodeMap = getStringColouredBagNodeMap(lines);

        return bagNodeMap.values()
                .stream()
                .map(colouredBag -> colouredBag.containsBagColour(keyString))
                .filter(aBoolean -> aBoolean)
                .count();
    }

    public static long processStringStreamPart2(String keyString, Stream<String> lines) {
        Map<String, ColouredBagNode> bagNodeMap = getStringColouredBagNodeMap(lines);

        ColouredBagNode keyBagNode = bagNodeMap.get(keyString);
        return keyBagNode.countBags();
    }

    private static Map<String, ColouredBagNode> getStringColouredBagNodeMap(Stream<String> lines) {
        Map<String, ColouredBag> bagMap = lines.map(ColouredBag::buildFrom)
                .collect(Collectors.toUnmodifiableMap(ColouredBag::getColour, Function.identity()));
        return bagMap.values()
                .stream()
                .map(bag -> ColouredBagNode.buildFrom(bag, bagMap))
                .collect(Collectors.toUnmodifiableMap(ColouredBagNode::getColour, Function.identity()));
    }
}

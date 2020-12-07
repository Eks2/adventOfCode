package day7;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor
@Value
public class ColouredBagNode {
    String colour;
    Map<ColouredBagNode, Integer> childBagToQuantityMap;

    public static ColouredBagNode buildFrom(ColouredBag bag, Map<String, ColouredBag> bagMap) {
        Map<ColouredBagNode, Integer> bagNodeToQuantityMap = bag.getChildBagToQuantityMap()
                .keySet()
                .stream()
                .collect(Collectors.toUnmodifiableMap(
                        s -> ColouredBagNode.buildFrom(bagMap.get(s), bagMap),
                        s -> bag.getChildBagToQuantityMap().get(s))
                );
        return new ColouredBagNode(bag.getColour(), bagNodeToQuantityMap);
    }

    public boolean containsBagColour(String bagColour) {
        return childBagToQuantityMap.keySet()
                .stream()
                .anyMatch(bagNode -> bagNode.getColour().equals(bagColour) || bagNode.containsBagColour(bagColour));
    }

    public long countBags() {
        return childBagToQuantityMap.keySet()
                .stream()
                .map(bagNode -> childBagToQuantityMap.get(bagNode)*(bagNode.countBags() + 1))
                .mapToLong(value -> value)
                .sum();
    }
}

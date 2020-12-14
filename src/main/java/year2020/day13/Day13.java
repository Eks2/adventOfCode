package year2020.day13;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day13 {
    public static long processStringStreamPart1(Stream<String> lines) {
        List<String> strings = lines.collect(Collectors.toUnmodifiableList());
        long departTime = Long.parseLong(strings.get(0));
        Pattern p = Pattern.compile("\\d+");
        Matcher matcher = p.matcher(strings.get(1));

        return matcher.results()
                .map(MatchResult::group)
                .map(Long::parseLong)
                .map(l -> Map.of("busId", l, "waitTime", l - (departTime % l)))
                .min((stringLongMap, t1) -> (int) (stringLongMap.get("waitTime") - t1.get("waitTime")))
                .map(stringLongMap -> stringLongMap.get("busId")*stringLongMap.get("waitTime"))
                .get();


    }

    public static long processStringStreamPart2(Stream<String> lines) {
        List<String> strings = lines.collect(Collectors.toUnmodifiableList());
        String[] split = strings.get(1).split(",");

        Map<Long, Integer> numberMap = IntStream.range(0, split.length)
                .filter(i -> !split[i].equals("x"))
                .boxed()
                .collect(Collectors.toMap(i -> Long.parseLong(split[i]), Function.identity()));

        long biggestNumber = numberMap.keySet()
                .stream()
                .mapToLong(Long::valueOf)
                .max()
                .getAsLong();
        long currentNumber = (long) numberMap.get(biggestNumber);
        numberMap.remove(biggestNumber);
        while (true) {
            boolean mapSatisfied = isMapSatisfied(numberMap, currentNumber);
            if (mapSatisfied) {
                return currentNumber;
            }
            currentNumber += biggestNumber;
        }

    }

    private static boolean isMapSatisfied(Map<Long, Integer> integerMap, long currentNumber) {
        return integerMap.keySet()
                .stream()
                .map(i -> (currentNumber - integerMap.get(i)) % i)
                .allMatch(aLong -> aLong == 0);
    }

}

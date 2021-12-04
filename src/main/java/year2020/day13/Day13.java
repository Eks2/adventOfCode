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
        List<String> strings = lines.toList();
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

        List<Long> longs = IntStream.range(0, split.length)
                .filter(i -> !split[i].equals("x"))
                .mapToObj(value -> Long.parseLong(split[value]))
                .collect(Collectors.toUnmodifiableList());

        long multiplier = longs.get(0);
        long startingNumber = numberMap.get(multiplier);

        for (int i=1; i<longs.size(); i++) {
            long newPrimeNumber = longs.get(i);
            long offset = numberMap.get(newPrimeNumber);
            startingNumber = findNumber(startingNumber, multiplier, newPrimeNumber, offset);
            multiplier = multiplier*newPrimeNumber;
        }
        return startingNumber;
    }

    private static long findNumber(long startingNumber, long multiplier, long newPrimeNumber, long offset) {
        boolean thing = true;
        while (thing) {
            startingNumber += multiplier;
            if (((startingNumber + offset) % newPrimeNumber) == 0) {
                thing = false;
            }
        }
        return startingNumber;
    }

}

package day7;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@AllArgsConstructor
@Value
public class ColouredBag {
    String colour;
    Map<String, Integer> childBagToQuantityMap;

    public static ColouredBag buildFrom(String string) {
        Pattern p1 = Pattern.compile("\\S+ \\S+");
        Matcher m1 = p1.matcher(string);
        if (!m1.find()) {
            throw new RuntimeException("Could not find bag");
        }

        Pattern p2 = Pattern.compile("(\\d+) (\\S+ \\S+)");
        Matcher m2 = p2.matcher(string);
        Map<String, Integer> map = m2.results()
                .collect(Collectors.toUnmodifiableMap(mr -> mr.group(2), mr -> Integer.valueOf(mr.group(1))));
        return new ColouredBag(m1.group(), map);
    }
}

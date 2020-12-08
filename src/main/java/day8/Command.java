package day8;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Value
public class Command {
    String instruction;
    int number;
    public static final Pattern PATTERN = Pattern.compile("(\\S+) (-?\\+?\\d+$)");

    public static Command buildFrom(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new RuntimeException("Could not find match");
        }
        return new Command(matcher.group(1), Integer.parseInt(matcher.group(2)));
    }
}

package year2020.day2;

import lombok.AllArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class PasswordLine {

    public static Pattern pattern = Pattern.compile("^(\\d+)-(\\d+) (\\w+): (\\w+)");

    Integer minNumber;
    Integer maxNumber;
    char character;
    String password;

    public static PasswordLine buildFrom(String string) {
        Matcher matcher = pattern.matcher(string);
        matcher.matches();
        return new PasswordLine(
                Integer.parseInt(matcher.group(1)),
                Integer.parseInt(matcher.group(2)),
                matcher.group(3).charAt(0),
                matcher.group(4)
        );
    }

    public boolean isValidStep1() {
        long count = password.chars().filter(ch -> ch == character).count();
        return minNumber <= count && count <= maxNumber;
    }

    public boolean isValidStep2() {
        return password.charAt(minNumber - 1) == character ^ password.charAt(maxNumber - 1) == character;
    }
}

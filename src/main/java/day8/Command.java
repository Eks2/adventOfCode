package day8;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Value
public class Command {
    public static final Pattern PATTERN = Pattern.compile("(\\S+) (-?\\+?\\d+$)");

    int lineNumber;
    String instruction;
    int number;

    public static Command buildFrom(int lineNumber, String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (!matcher.find()) {
            throw new RuntimeException("Could not find match");
        }
        return new Command(lineNumber, matcher.group(1), Integer.parseInt(matcher.group(2)));
    }

    public Command getFlippedCommand() {
        switch (instruction) {
            case "jmp":
                return new Command(lineNumber, "nop", number);
            case "nop":
                return new Command(lineNumber, "jmp", number);
            default:
                return this;
        }
    }

    public boolean isPotentiallyBadCommand() {
        return instruction.equals("jmp") || instruction.equals("nop");
    }
}

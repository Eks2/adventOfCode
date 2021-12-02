package year2021.day2;

import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day2 {
    public static long processStringStreamPart1(Stream<String> lines) {
        return lines.map(Instruction::buildFrom).reduce(new PositionPart1(), PositionPart1::add, PositionPart1::combine).answer();
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        return lines.map(Instruction::buildFrom).reduce(new PositionPart2(), PositionPart2::add, PositionPart2::combine).answer();
    }
}

record Instruction(String word, int number) {
    static Pattern PATTERN = Pattern.compile("^(\\w+) (\\d+)");
    static Instruction buildFrom(String inputLine) {
        Matcher matcher = PATTERN.matcher(inputLine);
        matcher.find();
        return new Instruction(matcher.group(1), Integer.parseInt(matcher.group(2)));
    }
}

@Data
class PositionPart1 {

    private int x = 0;
    private int y = 0;

    PositionPart1 add(Instruction instruction) {
        switch (instruction.word()) {
            case "forward" -> x += instruction.number();
            case "down" -> y += instruction.number();
            case "up" -> y -= instruction.number();
        }
        return this;
    }

    PositionPart1 combine(PositionPart1 positionPart1) {
        x += positionPart1.getX();
        y += positionPart1.getY();
        return this;
    }

    int answer() {
        return x * y;
    }
}

@Data
class PositionPart2 {

    private int x = 0;
    private int y = 0;
    private int aim = 0;

    PositionPart2 add(Instruction instruction) {
        switch (instruction.word()) {
            case "forward" -> {
                x += instruction.number();
                y += aim * instruction.number();
            }
            case "down" -> aim += instruction.number();
            case "up" -> aim -= instruction.number();
        }
        return this;
    }

    PositionPart2 combine(PositionPart2 positionPart2) {
        x += positionPart2.getX();
        y += positionPart2.getY();
        return this;
    }

    int answer() {
        return x * y;
    }
}
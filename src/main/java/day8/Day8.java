package day8;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day8 {
    public static long processStringStreamPart1(Stream<String> lines) {
        List<Command> commandList = lines.map(Command::buildFrom).collect(Collectors.toUnmodifiableList());
        Program program = new Program(commandList);
        return program.findAccumulatorTotal();
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        return 0L;
    }
}

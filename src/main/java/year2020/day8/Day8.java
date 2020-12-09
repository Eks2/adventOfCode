package year2020.day8;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day8 {
    public static long processStringStreamPart1(Stream<String> lines) {
        List<Command> commandList = getCommands(lines);
        Program program = new Program(commandList);
        return program.findAccumulatorTotal();
    }

    public static long processStringStreamPart2(Stream<String> lines) {
        List<Command> commandList = getCommands(lines);
        Optional<Program> finiteProgram = commandList.stream()
                .filter(Command::isPotentiallyBadCommand)
                .map(command -> commandList.stream()
                        .map(cmd -> {
                            if (cmd.equals(command)) {
                                return cmd.getFlippedCommand();
                            } else {
                                return cmd;
                            }
                        })
                        .collect(Collectors.toUnmodifiableList())
                )
                .map(Program::new)
                .filter(program -> !program.isProgramInfinite())
                .findAny();
        return finiteProgram.get().findAccumulatorTotal();
    }

    public static boolean isProgramInfinite(Stream<String> lines) {
        List<Command> commandList = getCommands(lines);
        Program program = new Program(commandList);
        return program.isProgramInfinite();
    }

    private static List<Command> getCommands(Stream<String> lines) {
        List<String> stringList = lines.collect(Collectors.toUnmodifiableList());
        return IntStream.range(0, stringList.size())
                .mapToObj(i -> Command.buildFrom(i, stringList.get(i)))
                .collect(Collectors.toUnmodifiableList());
    }
}

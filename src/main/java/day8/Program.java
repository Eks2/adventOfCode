package day8;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Value
public class Program {
    List<Command> commandList;

    private Stream<Command> commandStream() {
        return Stream.iterate(commandList.get(0), this::hasNextCommand, this::getNextCommand);
    }

    private boolean hasNextCommand(Command command) {
        return command.getLineNumber() < commandList.size();
    }

    private Command getNextCommand(Command command) {
        if (command.getLineNumber() + 1 >= commandList.size()) {
            return new Command(command.getLineNumber() + 1, command.getInstruction(), command.getNumber());
        }
        if (command.getInstruction().equals("jmp")) {
            return commandList.get(command.getLineNumber() + command.getNumber());
        }

        return commandList.get(command.getLineNumber() + 1);
    }

    public int findAccumulatorTotal() {
        Set<Command> visitedCommands = new java.util.HashSet<>();
        return commandStream()
                .takeWhile(command -> !visitedCommands.contains(command))
                .peek(visitedCommands::add)
                .filter(command -> command.getInstruction().equals("acc"))
                .map(Command::getNumber)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public boolean isProgramInfinite() {
        Set<Command> visitedCommands = new java.util.HashSet<>();
        return commandStream()
                .anyMatch(command -> {boolean contains = visitedCommands.contains(command); visitedCommands.add(command); return contains;});
    }
}

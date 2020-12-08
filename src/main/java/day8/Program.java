package day8;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@AllArgsConstructor
@Value
public class Program {
    List<Command> commandList;

    private Stream<Integer> indexStream() {
        return Stream.iterate(0, this::getNextIndex);
    }

    private int getNextIndex(int index) {
        Command command = commandList.get(index);
        if (command.getInstruction().equals("jmp")) {
            return index + command.getNumber();
        }
        return index + 1;
    }

    public int findAccumulatorTotal() {
        Set<Integer> visitedIndices = new java.util.HashSet<>();
        return indexStream()
                .takeWhile(integer -> !visitedIndices.contains(integer))
                .peek(visitedIndices::add)
                .map(commandList::get)
                .filter(command -> command.getInstruction().equals("acc"))
                .map(Command::getNumber)
                .mapToInt(Integer::valueOf)
                .sum();
    }
}

package year2020.day3;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MapLine {
    String line;

    boolean isTreeAt(int pos) {
        return line.charAt(pos % line.length()) == '#';
    }
}

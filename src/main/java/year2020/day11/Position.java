package year2020.day11;

import lombok.AllArgsConstructor;
import lombok.Value;

@AllArgsConstructor
@Value
public class Position {
    int x;
    int y;

    public boolean isInBounds(int width, int height) {
        return y >= 0 && y < height && x >= 0 && x < width;
    }

    public Position add(Position direction) {
        return new Position(direction.getX() + x, direction.getY() + y);
    }
}

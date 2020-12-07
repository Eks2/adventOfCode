package day5;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SeatLine {
    int row;
    int column;

    public static SeatLine buildFrom(String line) {
        String rowString = line.substring(0, 7).replaceAll("F", "0").replaceAll("B", "1");
        String columnString = line.substring(7, 10).replaceAll("L", "0").replaceAll("R", "1");
        return new SeatLine(Integer.parseInt(rowString, 2), Integer.parseInt(columnString, 2));
    }

    public int getSeatId(){
        return row*8 + column;
    }
}

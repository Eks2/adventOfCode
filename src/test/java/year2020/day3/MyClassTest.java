package year2020.day3;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MyClassTest {
    @Test
    void testExampleStep1(){
        List<MapLine> mapLineList = List.of(
                new MapLine("..##......."),
                new MapLine("#...#...#.."),
                new MapLine(".#....#..#."),
                new MapLine("..#.#...#.#"),
                new MapLine(".#...##..#."),
                new MapLine("..#.##....."),
                new MapLine(".#.#.#....#"),
                new MapLine(".#........#"),
                new MapLine("#.##...#..."),
                new MapLine("#...##....#"),
                new MapLine(".#..#...#.#")
        );

        Toboggan toboggan = new Toboggan(3, 1);
        long actualTreesHit = toboggan.surf(mapLineList);
        assertEquals(7, actualTreesHit);
    }

    @Test
    void testInputStep1() throws IOException {
        Path filePath = Paths.get("src/test/java/year2020/day3/input");
        List<MapLine> mapLineList = Files.lines(filePath)
                .map(MapLine::new)
                .collect(Collectors.toUnmodifiableList());

        Toboggan toboggan = new Toboggan(3, 1);
        long actualTreesHit = toboggan.surf(mapLineList);
        assertEquals(252, actualTreesHit);
    }

    @Test
    void testInputStep2() throws IOException {
        Path filePath = Paths.get("src/test/java/year2020/day3/input");
        List<MapLine> mapLineList = Files.lines(filePath)
                .map(MapLine::new)
                .collect(Collectors.toUnmodifiableList());

        Toboggan toboggan1 = new Toboggan(1, 1);
        Toboggan toboggan2 = new Toboggan(3, 1);
        Toboggan toboggan3 = new Toboggan(5, 1);
        Toboggan toboggan4 = new Toboggan(7, 1);
        Toboggan toboggan5 = new Toboggan(1, 2);

        long actualTreesHit = toboggan1.surf(mapLineList)*
                toboggan2.surf(mapLineList)*
                toboggan3.surf(mapLineList)*
                toboggan4.surf(mapLineList)*
                toboggan5.surf(mapLineList);
        assertEquals(2608962048L, actualTreesHit);
    }
}
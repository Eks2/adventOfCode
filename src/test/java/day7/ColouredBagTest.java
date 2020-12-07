package day7;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ColouredBagTest {

    @Test
    void testColouredBag() {
        ColouredBag actualColouredBag =
                ColouredBag.buildFrom("light red bags contain 1 bright white bag, 2 muted yellow bags.");
        ColouredBag expectedColouredBag = new ColouredBag("light red", Map.of("bright white", 1, "muted yellow", 2));
        assertThat(actualColouredBag).isEqualTo(expectedColouredBag);
    }

}
package day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CommandTest {
    @Test
    void testCommandNegative() {
        Command actualCommand = Command.buildFrom("acc -99");
        Command expectedCommand = new Command("acc", -99);
        assertThat(actualCommand).isEqualTo(expectedCommand);
    }

    @Test
    void testCommandPositive() {
        Command actualCommand = Command.buildFrom("acc +99");
        Command expectedCommand = new Command("acc", 99);
        assertThat(actualCommand).isEqualTo(expectedCommand);
    }
}
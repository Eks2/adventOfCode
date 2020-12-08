package day8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProgramTest {
    @Test
    void testProgram(){
        Program program = new Program(List.of(new Command(0, "acc", 5)));
        assertThat(program.findAccumulatorTotal()).isEqualTo(5);
    }

}
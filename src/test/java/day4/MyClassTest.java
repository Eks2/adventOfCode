package day4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {
    @Test
    void testExampleStep1() throws IOException {
        String string = getPassportStrings();

        Pattern pattern = Pattern.compile("([\\w]+):([\\w#]+)");
        long count = string.lines()
                .map(pattern::matcher)
                .map(Matcher::results)
                .map(matchResultStream -> matchResultStream.collect(Collectors.toMap(mr -> mr.group(1), mr -> mr.group(2))))
                .map(Passport::new)
                .filter(Passport::areRequiredFieldsPresent)
                .count();
        assertEquals(260, count);
}
    @Test
    void testExampleStep2() throws IOException {
        String string = getPassportStrings();

        Pattern pattern = Pattern.compile("([\\w]+):([\\w#]+)");
        long count = string.lines()
                .map(pattern::matcher)
                .map(Matcher::results)
                .map(matchResultStream -> matchResultStream.collect(Collectors.toMap(mr -> mr.group(1), mr -> mr.group(2))))
                .map(Passport::new)
                .filter(Passport::isValid)
                .count();
        assertEquals(153, count);
}

    private String getPassportStrings() throws IOException {
        String string = Files.readString(Paths.get("src/test/java/day4/input"));
        string = string.replaceAll("(?<!\n)\n(?!\n)", " ");
        string = string.replaceAll("\n\n:", "");
        return string;
    }

}
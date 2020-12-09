package year2020.day4;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
public class Passport {
    Map<String, String> map;

    static List<String> REQUIRED_FIELDS = List.of("ecl", "pid", "eyr", "hcl", "byr", "iyr", "hgt");
    static List<String> VALID_EYE_COLOURS = List.of("amb","blu","brn","gry","grn","hzl","oth");
    boolean areRequiredFieldsPresent() {
        return REQUIRED_FIELDS.stream().allMatch(map::containsKey);
    }

    boolean isValid(){
        return isByrValid() && isIyrValid() && isEyrValid() && isHgtValid() && isHclValid() && isEclValid() && isPidValid();
    }

    private boolean isByrValid() {
        String code = "byr";
        if (!map.containsKey(code)) {
            return false;
        }
        int number = Integer.parseInt(map.get(code));
        return number >= 1920 && number <= 2002;
    }

    private boolean isIyrValid() {
        String code = "iyr";
        if (!map.containsKey(code)) {
            return false;
        }
        int number = Integer.parseInt(map.get(code));
        return number >= 2010 && number <= 2020;
    }

    private boolean isEyrValid() {
        String code = "eyr";
        if (!map.containsKey(code)) {
            return false;
        }
        int number = Integer.parseInt(map.get(code));
        return number >= 2020 && number <= 2030;
    }

    private boolean isHgtValid() {
        String code = "hgt";
        if (!map.containsKey(code)) {
            return false;
        }

        String value = map.get(code);
        Pattern pattern = Pattern.compile("(\\d+)(\\w+)");
        Matcher m = pattern.matcher(value);
        if (!m.matches()) {
            return false;
        }
        int number = Integer.parseInt(m.group(1));
        String units = m.group(2);
        switch (units) {
            case "cm":
                return number >= 150 && number <= 193;
            case "in":
                return number >= 59 && number <= 76;
            default:
                return false;
        }
    }

    private boolean isHclValid() {
        String code = "hcl";
        if (!map.containsKey(code)) {
            return false;
        }
        String value = map.get(code);
        Pattern pattern = Pattern.compile("(#)(\\w+)");
        Matcher m = pattern.matcher(value);
        if (!m.matches()) {
            return false;
        }
        return m.group(2).length() == 6;
    }

    private boolean isEclValid() {
        String code = "ecl";
        if (!map.containsKey(code)) {
            return false;
        }
        String value = map.get(code);
        return VALID_EYE_COLOURS.contains(value);
    }

    private boolean isPidValid() {
        String code = "pid";
        if (!map.containsKey(code)) {
            return false;
        }
        String value = map.get(code);
        Pattern pattern = Pattern.compile("\\d+");
        Matcher m = pattern.matcher(value);
        if (!m.matches()) {
            return false;
        }
        return m.group().length() == 9;
    }
}

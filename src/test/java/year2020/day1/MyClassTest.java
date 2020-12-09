package year2020.day1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    @Test
    void testPageExampleStep1() {
        MyClass myClass = new MyClass();
        List<Integer> inputList = List.of(1721, 979, 366, 299, 675, 1456);
        List<Integer> results = myClass.findPairSumProduct(2020, inputList).get();
        Integer actualResult = results.get(0)*results.get(1);
        assertEquals(514579, actualResult);
    }

    @Test
    void testInputStep1() throws IOException {
        MyClass myClass = new MyClass();
        List<Integer> integers = getIntegers();

        List<Integer> results = myClass.findPairSumProduct(2020, integers).get();
        Integer actualResult = results.get(0)*results.get(1);
        assertEquals(1019571, actualResult);
    }

    @Test
    void testPageExampleStep2() {
        MyClass myClass = new MyClass();
        List<Integer> inputList = List.of(1721, 979, 366, 299, 675, 1456);
        List<Integer> results = myClass.findPairTripletProduct(2020, inputList).get();
        Integer actualResult = results.get(0)*results.get(1)*results.get(2);
        assertEquals(241861950, actualResult);
    }

    @Test
    void testInputStep2() throws IOException {
        MyClass myClass = new MyClass();
        List<Integer> integers = getIntegers();

        List<Integer> results = myClass.findPairTripletProduct(2020, integers).get();
        Integer actualResult = results.get(0)*results.get(1)*results.get(2);
        assertEquals(100655544, actualResult);
    }

    private List<Integer> getIntegers() throws IOException {
        Path filePath = Paths.get("src/test/java/year2020/day1/input");
        Scanner scanner = new Scanner(filePath);
        List<Integer> integers = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                integers.add(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
        return integers;
    }
}
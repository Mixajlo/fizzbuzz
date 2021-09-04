package com.jankov.fizzbuzz;

import static java.util.Map.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {

    @Test
    void verifyNumberGeneratorProvidesCorrectValues() {
        // verify generated numbers are multiple only of 3
        IntStream.range(0, 100)
                .forEach(i -> {
                    int value = generateNumberMultipleOnlyBy3();
                    assertEquals(0, value % 3);
                    assertNotEquals(0, value % 5);
                    assertNotEquals(0, value % 7);
                });

        // verify generated numbers are multiple only of 5
        IntStream.range(0, 100)
                .forEach(i -> {
                    int value = generateNumberMultipleOnlyBy5();
                    assertNotEquals(0, value % 3);
                    assertEquals(0, value % 5);
                    assertNotEquals(0, value % 7);
                });

        // verify generated numbers are multiple only of 7
        IntStream.range(0, 100)
                .forEach(i -> {
                    int value = generateNumberMultipleOnlyBy7();
                    assertNotEquals(0, value % 3);
                    assertNotEquals(0, value % 5);
                    assertEquals(0, value % 7);
                });

        // verify generated numbers are multiple only of 3 and 5
        IntStream.range(0, 100)
                .forEach(i -> {
                    var value = generateNumberMultipleBy(of(
                            3, true,
                            5, true,
                            7, false
                    ));
                    assertEquals(0, value % 3);
                    assertEquals(0, value % 5);
                    assertNotEquals(0, value % 7);
                });

        // verify generated numbers are multiple only of 3 and 7
        IntStream.range(0, 100)
                .forEach(i -> {
                    var value = generateNumberMultipleBy(of(
                            3, true,
                            5, false,
                            7, true
                    ));
                    assertEquals(0, value % 3);
                    assertNotEquals(0, value % 5);
                    assertEquals(0, value % 7);
                });

        // verify generated numbers are multiple only of 3, 5 and 7
        IntStream.range(0, 100)
                .forEach(i -> {
                    var value = generateNumberMultipleBy(of(
                            3, true,
                            5, true,
                            7, true
                    ));
                    assertEquals(0, value % 3);
                    assertEquals(0, value % 5);
                    assertEquals(0, value % 7);
                });
    }

    public static Integer generateNumberMultipleOnlyBy3() {
        return generateNumberMultipleBy(of(
                3, true,
                5, false,
                7, false
        ));
    }

    public static Integer generateNumberMultipleOnlyBy5() {
        return generateNumberMultipleBy(of(
                3, false,
                5, true,
                7, false
        ));
    }

    public static Integer generateNumberMultipleOnlyBy7() {
        return generateNumberMultipleBy(of(
                3, false,
                5, false,
                7, true
        ));
    }

    public static int generateNumberMultipleBy(Map<Integer, Boolean> multipliers) {
        var productOfMultipliers = calculateProductOfMultipliers(multipliers);
        int value = (int) (Math.random() * 10000) * productOfMultipliers;
        System.out.println("Generated value " + value);
        while (isValueNonDividableAgainstNonMultipliers(value, multipliers)) {
            System.out.println("Miss. Incrementing value by " + productOfMultipliers);
            value += productOfMultipliers;
        }
        System.out.println(value);
        return value;
    }

    private static Integer calculateProductOfMultipliers(Map<Integer, Boolean> multipliers) {
        return multipliers.keySet().stream().filter(multipliers::get).reduce(1, (a, b) -> a * b);
    }

    private static boolean isValueNonDividableAgainstNonMultipliers(int value, Map<Integer, Boolean> multipliers) {
        boolean result = false;
        for (Integer multiplier : multipliers.keySet()) {
            if (!multipliers.get(multiplier)) {
                result |= (value % multiplier == 0);
            }
        }
        return result;
    }
}

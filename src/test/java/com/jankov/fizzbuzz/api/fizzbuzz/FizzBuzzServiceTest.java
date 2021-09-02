package com.jankov.fizzbuzz.api.fizzbuzz;

import static com.jankov.fizzbuzz.api.fizzbuzz.FizzBuzzConstants.*;
import static java.util.Map.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Map;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

class FizzBuzzServiceTest {

    @InjectMocks
    FizzBuzzService fizzBuzzService;

    @Test
    void whenEntryIsOnlyMultipleOf3() {
        var result = fizzBuzzService.calculateEntry(generateNumberMultipleOnlyBy3());
        assertEquals(FIZZ.toLowerCase(), result);
    }

    @Test
    void whenEntryIsOnlyMultipleOf5() {
        var result = fizzBuzzService.calculateEntry(generateNumberMultipleOnlyBy5());
        assertEquals(BUZZ.toLowerCase(), result);
    }

    @Test
    void whenEntryIsMultipleOf3And5() {
        var result = fizzBuzzService.calculateEntry(generateNumberMultipleBy(of(3, true, 5, true)));
        assertEquals((FIZZ + BUZZ).toLowerCase(), result);
    }

    @Test
    void verifyNumberGeneratorProvidesCorrectValues() {
        IntStream.range(0, 10)
                .forEach(i -> {
                    int value = generateNumberMultipleOnlyBy3();
                    assertEquals(0, value % 3);
                    assertNotEquals(0, value % 5);
                });

        IntStream.range(0, 10)
                .forEach(i -> {
                    var value = generateNumberMultipleBy(of(
                            3, true,
                            5, true
                    ));
                    assertEquals(0, value % 3);
                    assertEquals(0, value % 5);
                });

        IntStream.range(0, 10)
                .forEach(i -> {
                    var value = generateNumberMultipleBy(of(
                            3, true,
                            5, true
                    ));
                    assertEquals(0, value % 3);
                    assertEquals(0, value % 5);
                });
    }

    private Integer generateNumberMultipleOnlyBy3() {
        return generateNumberMultipleBy(of(
                3, true,
                5, false
        ));
    }

    private Integer generateNumberMultipleOnlyBy5() {
        return generateNumberMultipleBy(of(
                3, false,
                5, true
        ));
    }

    private int generateNumberMultipleBy(Map<Integer, Boolean> multipliers) {
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

    private Integer calculateProductOfMultipliers(Map<Integer, Boolean> multipliers) {
        return multipliers.keySet().stream().filter(multipliers::get).reduce(1, (a, b) -> a * b);
    }

    private boolean isValueNonDividableAgainstNonMultipliers(int value, Map<Integer, Boolean> multipliers) {
        boolean result = false;
        for (Integer multiplier : multipliers.keySet()) {
            if (!multipliers.get(multiplier)) {
                result |= (value % multiplier == 0);
            }
        }
        return result;
    }
}

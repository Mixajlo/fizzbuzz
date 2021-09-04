package com.jankov.fizzbuzz.api.fizzbuzz;

import static com.jankov.fizzbuzz.RandomNumberGeneratorTest.*;
import static com.jankov.fizzbuzz.api.fizzbuzz.FizzBuzzConstants.*;
import static java.util.Map.of;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
    void whenEntryIsOnlyMultipleOf7() {
        var result = fizzBuzzService.calculateEntry(generateNumberMultipleOnlyBy7());
        assertEquals(BAZZ, result);
    }

    @Test
    void whenEntryIsMultipleOf3And5() {
        var result = fizzBuzzService.calculateEntry(
                generateNumberMultipleBy(of(3, true, 5, true, 7, false)));
        assertEquals((FIZZ + BUZZ).toLowerCase(), result);
    }

    @Test
    void whenEntryIsMultipleOf3And7() {
        var result = fizzBuzzService.calculateEntry(
                generateNumberMultipleBy(of(3, true, 5, false, 7, true)));
        assertEquals(FIZZ + BAZZ, result);
    }

    @Test
    void whenEntryIsMultipleOf5And7() {
        var result = fizzBuzzService.calculateEntry(
                generateNumberMultipleBy(of(3, false, 5, true, 7, true)));
        assertEquals(BUZZ + BAZZ, result);
    }

    @Test
    void whenEntryIsMultipleOf3And5And7() {
        var result = fizzBuzzService.calculateEntry(
                generateNumberMultipleBy(of(3, true, 5, true, 7, true)));
        assertEquals(FIZZ + BUZZ + BAZZ, result);
    }

    @Test
    void whenEntryIsNotMultipleOf3And5And7() {
        var entry = generateNumberMultipleBy(of(3, false, 5, false, 7, false));
        var result = fizzBuzzService.calculateEntry(entry);
        assertEquals(entry, result);
    }
}
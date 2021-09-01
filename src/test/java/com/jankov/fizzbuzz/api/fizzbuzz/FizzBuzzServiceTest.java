package com.jankov.fizzbuzz.api.fizzbuzz;

import static com.jankov.fizzbuzz.api.fizzbuzz.FizzBuzzConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FizzBuzzServiceTest {

    @Test
    void whenEntryIsOnlyMultipleOf3() {
        assertEquals(FIZZ.toLowerCase(), "fizz");
    }

    @Test
    void whenEntryIsOnlyMultipleOf5() {
        assertEquals(BUZZ.toLowerCase(), "buzz");
    }

    @Test
    void whenEntryIsMultipleOf3And5() {
        assertEquals((FIZZ + BUZZ).toLowerCase(), "fizzbuzz");
    }
}
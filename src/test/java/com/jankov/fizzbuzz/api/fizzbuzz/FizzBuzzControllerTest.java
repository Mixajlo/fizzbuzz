package com.jankov.fizzbuzz.api.fizzbuzz;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
class FizzBuzzControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FizzBuzzService service;

    @Test
    void resultIsFizzWhenEntryIsMultipleOf3() throws Exception {
        var validEntry = 3;
        when(service.calculateEntry(validEntry)).thenReturn("fizz");
        mockMvc.perform(get("/fizzbuzz?entry=" + validEntry))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is("fizz")));
    }

    @Test
    void resultIsBuzzWhenEntryIsMultipleOf5() throws Exception {
        var validEntry = 5;
        when(service.calculateEntry(validEntry)).thenReturn("buzz");
        mockMvc.perform(get("/fizzbuzz?entry=" + validEntry))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is("buzz")));
    }

    @Test
    void resultIsFizzBuzzWhenEntryIsMultipleOf3And5() throws Exception {
        var validEntry = 15;
        when(service.calculateEntry(validEntry)).thenReturn("fizzbuzz");
        mockMvc.perform(get("/fizzbuzz?entry=" + validEntry))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is("fizzbuzz")));
    }

    @Test
    void resultIsEntryWhenEntryIsNotMultiple() throws Exception {
        var nonValidEntry = 4;
        when(service.calculateEntry(nonValidEntry)).thenReturn(nonValidEntry);
        mockMvc.perform(get("/fizzbuzz?entry=" + nonValidEntry))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data", is(nonValidEntry)));
    }

    @Test
    void resultIsArrayWhenEntryIsMissing() throws Exception {
        var array = new Object[] {1, 2, "fizz", 4, "buzz"};
        when(service.calculateEntry(eq(null))).thenReturn(array);
        mockMvc.perform(get("/fizzbuzz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data", hasSize(5)))
                .andExpect(jsonPath("$.data", hasItem(1)))
                .andExpect(jsonPath("$.data", hasItem(2)))
                .andExpect(jsonPath("$.data", hasItem("fizz")))
                .andExpect(jsonPath("$.data", hasItem(4)))
                .andExpect(jsonPath("$.data", hasItem("buzz")));
    }
}
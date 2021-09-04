package com.jankov.fizzbuzz.api.fizzbuzz;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FizzBuzzControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    public void ifEntryIsMultipleOf3ReturnFizz() {
        var entry = 3;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"fizz\"");
    }

    @Test
    public void ifEntryIsMultipleOf5ReturnBuzz() {
        var entry = 5;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"buzz\"");
    }

    @Test
    public void ifEntryIsMultipleOf7ReturnBazz() {
        var entry = 7;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"Bazz\"");
    }

    @Test
    public void ifEntryIsMultipleOf3And5ReturnFizzBuzz() {
        var entry = 3 * 5;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"fizzbuzz\"");
    }

    @Test
    public void ifEntryIsMultipleOf3And7ReturnFizzBazz() {
        var entry = 3 * 7;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"FizzBazz\"");
    }

    @Test
    public void ifEntryIsMultipleOf5And7ReturnBuzzBazz() {
        var entry = 5 * 7;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"BuzzBazz\"");
    }

    @Test
    public void ifEntryIsMultipleOf3And5And7ReturnFizzBuzzBazz() {
        var entry = 3 * 5 * 7;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"FizzBuzzBazz\"");
    }
}
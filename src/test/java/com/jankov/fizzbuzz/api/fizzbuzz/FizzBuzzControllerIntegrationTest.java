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
    public void greetingShouldReturnDefaultMessage() {
        var entry = 5;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":\"" + entry);
    }
}
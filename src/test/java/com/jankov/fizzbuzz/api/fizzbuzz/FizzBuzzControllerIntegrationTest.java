package com.jankov.fizzbuzz.api.fizzbuzz;

import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

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

    @Test
    public void ifEntryIsNonMultipleReturnEntry() {
        var entry = 1;
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=" + entry,
                String.class)).contains("data\":" + entry);
    }

    @Test
    public void returnErrorIfEntryIsNotNumber() {
        var response = restTemplate.getForEntity("http://localhost:" + port + "/fizzbuzz?entry=e", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void ifNoEntryProvidedReturnArrayOf100() throws JSONException {
        var response = restTemplate.getForObject("http://localhost:" + port + "/fizzbuzz?entry=", String.class);
        JSONArray data = new JSONObject(response).getJSONArray("data");
        assertThat(data.length()).isEqualTo(100);
        assertThat((Integer)data.get(0)).isEqualTo(1); // for entry 1
        assertThat(data.get(2)).isEqualTo("fizz"); // for entry 3
        assertThat(data.get(4)).isEqualTo("buzz"); // for entry 5
        assertThat(data.get(6)).isEqualTo("Bazz"); // for entry 7
        assertThat(data.get(14)).isEqualTo("fizzbuzz"); // for entry 15
        assertThat(data.get(20)).isEqualTo("FizzBazz"); // for entry 21
        assertThat(data.get(34)).isEqualTo("BuzzBazz"); // for entry 35
    }
}
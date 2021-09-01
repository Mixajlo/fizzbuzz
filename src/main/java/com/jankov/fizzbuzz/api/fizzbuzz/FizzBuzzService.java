package com.jankov.fizzbuzz.api.fizzbuzz;

import org.springframework.stereotype.Service;

@Service
public class FizzBuzzService {

    public String calculateEntry(Integer entry) {
        return entry.toString();
    }
}

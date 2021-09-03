package com.jankov.fizzbuzz.api.fizzbuzz;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class FizzBuzzService {

    public Object calculateEntry(@Nullable Integer entry) {
        if(entry == null) {
            // TODO calculate entry from 0 to 100
            return new Object[0];
        }
        // TODO calculate entry
        return entry.toString();
    }
}

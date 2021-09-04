package com.jankov.fizzbuzz.api.fizzbuzz;

import static com.jankov.fizzbuzz.api.fizzbuzz.FizzBuzzConstants.*;

import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FizzBuzzService {

    public Object calculateEntry(@Nullable Integer entry) {
        if (entry == null) {
            return calculateForFirstHundred();
        }
        return calculateResultForEntry(entry);
    }

    private Object[] calculateForFirstHundred() {
        return IntStream.range(1, 101).boxed().map(this::calculateResultForEntry).toArray(Object[]::new);
    }

    private Object calculateResultForEntry(Integer entry) {
        String validResult = "";
        if (entry % 3 == 0) {
            validResult = FIZZ;
        }
        if (entry % 5 == 0) {
            validResult += BUZZ;
        }
        if (entry % 7 == 0) {
            validResult += BAZZ;
        } else {
            validResult = validResult.toLowerCase();
        }

        return validResult.isEmpty() ? entry : validResult;
    }
}

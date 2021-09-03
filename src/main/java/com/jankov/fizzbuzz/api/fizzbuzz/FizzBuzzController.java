package com.jankov.fizzbuzz.api.fizzbuzz;

import com.jankov.fizzbuzz.api.common.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fizzbuzz")
public class FizzBuzzController {

    public final FizzBuzzService fizzBuzzService;

    @GetMapping
    public ResponseDTO<Object> fizzBuzzEndpoint(@RequestParam(required = false) Integer entry) {
        return ResponseDTO.builder()
                .withData(fizzBuzzService.calculateEntry(entry))
                .build();
    }
}

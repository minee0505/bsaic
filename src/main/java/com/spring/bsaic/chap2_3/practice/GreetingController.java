package com.spring.bsaic.chap2_3.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/api/v1")
public class GreetingController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Spring MVC!";
    }
}

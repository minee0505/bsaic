package com.spring.basic.chap2_3.practice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practice/api/v1")
public class BookController {

    @GetMapping("/books")
    public String getBooks(@RequestParam("author") String author,
                           @RequestParam("genre") String genre) {
        return "Author: " + author + ", Genre: " + genre;
    }
}

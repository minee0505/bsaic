package com.spring.basic.chap2_4.controller;

import com.spring.basic.chap2_4.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v2-4/books")
public class BookController2_4 {

    // 데이터 베이스 대용으로 책들을 모아서 관리
    Map<Long, Book> bookStore = new HashMap<>();

    private Long nextId = 1L;

    public BookController2_4() {
        bookStore.put(nextId, new Book(nextId, "클린코드", "로버트 마틴", 20000));
        nextId++;
        bookStore.put(nextId, new Book(nextId, "해리포터", "조앤 롤링", 10000));
        nextId++;
        bookStore.put(nextId, new Book(nextId, "삼국지", "나관중", 15000));
        nextId++;
    }

    // 1. 목록 조회
    @GetMapping("/sort")
    public List<Book> list(@RequestParam(value="id", required = false) String id,
                           @RequestParam(value="author", required = false) String author,
                           @RequestParam(value="price", required = false) String price)
    {
//        List<Book> booklist = new ArrayList<>();
//        for (Long key : bookStore.keySet()) {
//            booklist.add(bookStore.get(key));
//        }
        /*return new ArrayList<>(bookStore.values())
                .stream().sorted(Comparator.comparing(Book::getId))
                .toList();*/

        /*return new ArrayList<>(bookStore.values())
                .stream().sorted(Comparator.comparing(Book::getAuthor).reversed())
                .toList();*/

        return new ArrayList<>(bookStore.values())
                .stream()
                .sorted(Comparator.comparing(Book::getPrice).reversed())
                .toList();

    }

    // 2.개별 조회
    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {

        Book foundBook = bookStore.get(id);

        return foundBook;

    }

    // 3.도서 생성
    @PostMapping
    public String createBook(String title, String author, int price) {

        // 새 도서 객체를 생성
        Book book = new Book(nextId++, title, author, price);

        // 맵에 저장
        bookStore.put(book.getId(), book);

        return "도서 추가 완료: " + book.getId();

    }

    // 삭제 요청  /api/v2-4/99  -> 삭제 실패 메시지 응답
    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookStore.remove(id);

        return "도서 삭제 완료: " + id;
    }

    @PutMapping("/{id}")
    public String updateBook(
            String title,
            String author,
            int price,
            @PathVariable Long id
    ) {
        Book foundBook = bookStore.get(id);

        if (foundBook == null) {
            return id + "번 도서는 존재하지 않습니다.";
        }

        foundBook.updateBookInfo(title, author, price);

        return "도서 수정 완료: id-" +id;

    }

    @GetMapping("/count")
    public String count() {
        return "현재 저장된 도서의 개수: " +bookStore.size() +"권";
    }

}

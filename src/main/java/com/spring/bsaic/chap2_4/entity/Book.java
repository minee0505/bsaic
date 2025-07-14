package com.spring.bsaic.chap2_4.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    // null과 0의 차이: null은 가격자체가 설정이 안된 것, 0은 그냥 0원임
    private Long id;  // 기본값을 0이 아닌 null로 하려고 래퍼클래스 사용
    private String title;
    private String author;
    private int price;

    // 수정 편의 메서드
    public void updateBookInfo(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }
}

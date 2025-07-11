package com.spring.bsaic.chap2_3.entity;

import lombok.*;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor // 기본생성자
@AllArgsConstructor
public class Product {

    private Long serialNo;
    private String name;
    private int price;

}

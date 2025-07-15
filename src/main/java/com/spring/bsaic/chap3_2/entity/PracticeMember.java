package com.spring.bsaic.chap3_2.entity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PracticeMember {

    private String id;
    private String userId;
    private String message;
    private int rating;

}

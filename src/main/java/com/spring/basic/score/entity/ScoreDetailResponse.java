package com.spring.basic.score.entity;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreDetailResponse {

    private String fullName; // 전체 이름
    private int kor, eng, math;
    private int total;
    private double average;
    private int rank;
    private int totalCount; // 전체 학생 수

    public static ScoreDetailResponse of(Score score, int totalCount) {
        return ScoreDetailResponse.builder()
                .fullName(score.getName())
                .kor(score.getKor())
                .eng(score.getEng())
                .math(score.getMath())
                .total(score.getTotal())
                .average(score.getAverage())
                .rank(score.getRank())
                .totalCount(totalCount) // 전체 학생 수 추가
                .build();
    }

}

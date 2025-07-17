package com.spring.basic.score.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

// 학생 한명의 성적정보를 저장
@Setter @Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Score {
    private Long id; // 학번
    private String name; // 이름;
    private int kor, eng, math; // 국영수 점수

    @JsonProperty("sum")
    private int total; // 총점
    private double average; // 평균
    private int rank; // 석차

    public void calculateTotalAndAverage() {
        this.total = this.kor + this.eng + this.math;
        this.average = Math.round((this.total / 3.0) * 100) / 100.0;
    }

}

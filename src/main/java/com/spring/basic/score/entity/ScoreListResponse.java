package com.spring.basic.score.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreListResponse {
//    @JsonIgnore
    private Long id; // 학번

    @JsonProperty("maskingName")
    private String name; // 가운데 글자를 마스킹 (첫글자랑 마지막 글자 빼고)

    @JsonIgnore
    private int kor, eng, math; // 국영수 점수

    @JsonProperty("sum")
    private int total; // 총점

    @JsonProperty("avg")
    private double average; // 평균

    private int rank; // 석차

    //엔터티를 전달받아서 dto로 변환하는 정적 팩토리 메서드
    public static ScoreListResponse from(Score score) {
        return ScoreListResponse.builder()
                .id(score.getId())
                .name(maskingName(score.getName()))
                .total(score.getTotal())
                .average(score.getAverage())
                .rank(score.getRank())
                .build();
    }

    private static String maskingName(String originName) {
        if (originName.length() <= 2) {
            return originName.charAt(0) + "*";
        }
        return originName.charAt(0)
                + "*".repeat(originName.length() - 2)
                + originName.charAt(originName.length() - 1);
    }

}

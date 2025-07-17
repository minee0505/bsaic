package com.spring.basic.score.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreCreateDto {

    @JsonProperty("studentName")
    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @JsonProperty("korean")
    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하여야 합니다.")
    private int kor; // 국영수 점수

    @JsonProperty("english")
    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하여야 합니다.")
    private int eng;

    @Min(value = 0, message = "점수는 0점 이상이어야 합니다.")
    @Max(value = 100, message = "점수는 100점 이하여야 합니다.")
    private int math;

    public Score toEntity(long id) {
        Score score = Score.builder()
                .id(id) // 서버가 생성한 학번
                .name(this.name)
                .kor(this.kor)
                .eng(this.eng)
                .math(this.math)
                .build();
        score.calculateTotalAndAverage();
        return score;
    }

}

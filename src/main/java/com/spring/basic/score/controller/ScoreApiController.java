package com.spring.basic.score.controller;

import com.spring.basic.score.entity.Score;
import com.spring.basic.score.entity.ScoreCreateDto;
import com.spring.basic.score.entity.ScoreDetailResponse;
import com.spring.basic.score.entity.ScoreListResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/scores")
@Slf4j
public class ScoreApiController {

    private Map<Long, Score> scoreStore = new HashMap<>();
    private static long sequence = 0L;

    public ScoreApiController() {
        Score s1 = new Score(++sequence, "김수복", 97, 88, 79, 0, 0, 0);
        s1.calculateTotalAndAverage();
        Score s2 = new Score(++sequence, "박수포자", 55, 95, 15, 0, 0, 0);
        s2.calculateTotalAndAverage();
        Score s3 = new Score(++sequence, "김말클", 100, 48, 96, 0, 0, 0);
        s3.calculateTotalAndAverage();
        Score s4 = new Score(++sequence, "세종대왕", 63, 72, 58, 0, 0, 0);
        s4.calculateTotalAndAverage();

        scoreStore.put(s1.getId(), s1);
        scoreStore.put(s2.getId(), s2);
        scoreStore.put(s3.getId(), s3);
        scoreStore.put(s4.getId(), s4);

    }

    // 석차 계산 로직
    private void calculateRanks() {
        List<Score> scores = scoreStore.values().stream()
                .sorted(Comparator.comparingInt(Score::getTotal).reversed())
                .collect(Collectors.toList());

        for (int i = 0; i < scores.size(); i++) {
            scores.get(i).setRank(i + 1);
        }
    }

    // 전체 조회
    @GetMapping
    public ResponseEntity<?> scoreList(@RequestParam(defaultValue = "id") String sort) {

        calculateRanks(); // 목록 조회 시 항상 석차 재계산

        List<Score> sortedList = new ArrayList<>(scoreStore.values());

        // 정렬 처리
        switch (sort) {
            case "name":
                sortedList.sort(Comparator.comparing(Score::getName));
                break;
            case "average":
                sortedList.sort(Comparator.comparing(Score::getAverage).reversed());
                break;
            default: // id (학번)
                sortedList.sort(Comparator.comparing(Score::getId));
                break;
        }

        List<ScoreListResponse> responseList = sortedList.stream()
                .map(ScoreListResponse::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(responseList);
    }

    // 개별 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getScore(@PathVariable Long id) {
        if (!scoreStore.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        calculateRanks(); // 현재 석차 계산
        Score score = scoreStore.get(id);
        return ResponseEntity.ok(ScoreDetailResponse.from(score, scoreStore.size()));
    }

    // 성적 등록
    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody @Valid ScoreCreateDto dto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errorMap.put(err.getField(), err.getDefaultMessage());
            });

//            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors(errorMap));
            return ResponseEntity.badRequest().body(errorMap);
        }

        Score newScore = dto.toEntity(++sequence);
        scoreStore.put(newScore.getId(), newScore);

        log.info("성적 등록 완료: {}", newScore);
        return ResponseEntity.ok(scoreList("id")); // 등록 후 전체 목록 반환
    }


    // 성적 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable Long id) {
        if (!scoreStore.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        scoreStore.remove(id);
        return ResponseEntity.ok("delete success");
    }

    }


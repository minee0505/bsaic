package com.spring.basic.score.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @GetMapping("/score")
    public String scorePage() {
        return "score/score-page";
    }

    // 상세 정보 페이지로 이동하는 요청
    @GetMapping("/score/detail/{id}")
    public String scoreDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "score/score-detail";
    }
}
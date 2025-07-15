package com.spring.bsaic.chap3_2.controller;

import com.spring.bsaic.chap3_2.entity.PracticeMember;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v3-2/practice-members")
public class PracticeController {

    private Map<String, PracticeMember> memberStore = new HashMap<>();

    private static long nextId = 1;

    // 전체 조회
    @GetMapping
    public List<PracticeMember> list() {
        return new ArrayList<>(memberStore.values());
    }

    @PostMapping
    public String join(@RequestBody PracticeMember member) {
        member.setId(String.valueOf(nextId++));

        memberStore.put(member.getId(), member);
        return "새로운 평점 생성됨! -userId : " +member.getUserId();
    }

}

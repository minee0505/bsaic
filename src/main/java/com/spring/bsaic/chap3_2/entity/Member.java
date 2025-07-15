package com.spring.bsaic.chap3_2.entity;

import lombok.*;

import java.util.UUID;

@Getter @Setter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Builder.Default
    private String uid = UUID.randomUUID().toString(); // 회원 식별번호

    private String account;
    private String password;
    private String nickname;



    public Member(String password, String account, String nickname) {
        this.uid = UUID.randomUUID().toString();
        this.password = password;
        this.account = account;
        this.nickname = nickname;
    }

    /*private Member(Builder builder) {
        this.uid = UUID.randomUUID().toString();
        this.password = builder.password;
        this.account = builder.account;
        this.nickname = builder.nickname;
    }*/

    /*// 빌더 패턴 구현 - 생성자를 대체하는 것
    public static class Builder {
        // 원본 클래스랑 완벽하게 동일한 필드를 구성
        private String uid;
        private String account;
        private String password;
        private String nickname;

        public Builder() {}

        // 필드를 초기화하는 setter를 자기 필드명과 동일하게 생성
        public Builder account(String account) {
            // 자기 자신 객체를 리턴
            this.account = account;
            return this;
        }

        public Builder password(String password) {
            // 자기 자신 객체를 리턴
            this.password = password;
            return this;
        }

        public Builder nickname(String nickname) {
            // 자기 자신 객체를 리턴
            this.nickname = nickname;
            return this;
        }

        // 최종 연산에서는 원본 객체를 리턴
        public Member build() {
            return new Member(this);
        }

    }*/


}

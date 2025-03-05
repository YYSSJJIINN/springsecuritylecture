package com.ohgiraffers.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    /* 목차. 1-1. 사용자의 비밀번호를 BCrypt 암호화하기 위한 Bean 설정
     *  =================================================================================================
     *  BCryptPasswordEncoder를 사용한 이유(면접 질문 대비용)
     *   : BCrypt는 비밀번호 해싱(hashing)에 가장 많이 사용되는 알고리즘 중 하나이다.
     *   1. 보안성 : 해시 함수에 무작위 솔트(salt)를 적용하여 생성한다.
     *   2. 비용 증가 : 매개변수에 값을 주면 암호 생성 시간을 조절할 수 있어 무차별 공격을 어렵게 한다.
     *   3. 호환성 : 높은 보안 수준 및 데이터베이스에 저장하기 쉬운 특징.
     *   4. 알고리즘 신뢰성 : 논의 평가를 거친 알고리즘으로 보안 관련 로직에 문제없이 계속 사용 중.
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

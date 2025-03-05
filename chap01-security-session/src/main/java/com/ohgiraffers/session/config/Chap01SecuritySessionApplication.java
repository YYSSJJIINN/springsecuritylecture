package com.ohgiraffers.session.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ohgiraffers.session")
/* MyBatis를 연결해줬기 때문에 MapperScan도 어노테이션 달아줘야한다. */
@MapperScan(basePackages = "com.ohgiraffers.session", annotationClass = Mapper.class)
public class Chap01SecuritySessionApplication {

    public static void main(String[] args) {

        SpringApplication.run(Chap01SecuritySessionApplication.class, args);
    }

}

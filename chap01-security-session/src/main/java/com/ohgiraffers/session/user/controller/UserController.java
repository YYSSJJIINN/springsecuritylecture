package com.ohgiraffers.session.user.controller;

import com.ohgiraffers.session.user.model.dto.SignupDTO;
import com.ohgiraffers.session.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public void signup() {}

    @PostMapping("/signup")
    public ModelAndView signup(ModelAndView mv, SignupDTO newUserDTO) {
        System.out.println("newUserDTO = " + newUserDTO);

        /* 요청 속 form 데이터를 일일이 파싱하지 않고 커맨드 객체로 받아냈기 때문에
         * 회원가입 폼(SignupDTO) 데이터 통째로 서비스 레이어에 전달해서 비즈니스 로직을 호출할 수 있다.
         * ===================================================
         * 이 때, Service Layer로 세부 비즈니스 로직(DML)을 호출한 후, 그 반환형은 int가 아니라 Integer로 설정한다.
         * 이는 DML 작업 결과에 대한 Java 기반 애플리케이션 측에서 null값을 처리하기 위해서다.
         * 1. null을 반환할 때:
         *   - 데이터 삽입(DML) 작업 실패.
         *   - 중복된 사용자가 이미 존재하거나, 데이터베이스 연결 문제 또는 기타 예외가 발생했을 수 있음.
         * 2. 0을 반환할 때:
         *   - 데이터 삽입(DML) 작업은 성공, 하지만 실제로 레코드가 테이블에 삽입되지는 않음.
         *   - 비즈니스 로직 자체에 문제가 있거나, DDL 또는 DML 쿼리를 확인해봐야 함.
         * 3. 1을 반환할 때:
         *   - 데이터 삽입(DML) 작업 성공, 정상적으로 1개의 레코드가 삽입됨.
         * */
        Integer result = userService.regist(newUserDTO);

        return mv;
    }
}

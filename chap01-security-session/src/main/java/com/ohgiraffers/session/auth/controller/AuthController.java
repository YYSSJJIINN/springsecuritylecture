package com.ohgiraffers.session.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")   // 로그인 페이지로 이동시켜줌
    public void login() {}

//    @PostMapping("/login")
//    // 다음은 핸들러 메서드로, Handler(Controller)의 위치에 있다.
//    // 이 위치가 이미 필터에서 걸러져 필터가 필요없는 위치기 때문에 작성하지 않는다.
//    public String login(Model model) {}
}

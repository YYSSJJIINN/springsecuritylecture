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
         * 이 때, Service Layer로 세부 비즈니스 로직(DML)을 호출한 후, 그 반환형은 int(프리미티브=원시타입)) 아니라 Integer(매퍼클래스=레퍼런스타입)로 설정한다.
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

        /* 사용자에게 입력받았던 newUserDTO를 여기에서 사용한다. */
        // userService했을 때, Integer타입값의 result를 받을 것이다.
        Integer result = userService.regist(newUserDTO);

        /* 비즈니스 로직 성공 여부에 따라서 Dispatcher Servlet에 반환할 View와 Model을 세팅. */
        String message = null;

        /* 실패한 경우에 작업하던 페이지에 그대로 남아있게 작성해주면 되낟.*/
        if(result == null ) {
            message = "이미 해당 정보로 가입된 회원이 존재합니다.";
            System.out.println("message = " + message);
            mv.setViewName("user/signup");
        } else if (result == 0) {
            message = "회원 가입에 실패했습니다. 다시 시도해주세요.";
            System.out.println("message = " + message);
            mv.setViewName("user/signup");
        } else if (result >= 1) {
            /* 얘만 성공했으므로, 성공 후에 이동할 로그인페이지로 이동하게 작성해주면 된다.*/
            message = "회원 가입이 성공적으로 완료되었습니다.";
            System.out.println("message = " + message);
            mv.setViewName("auth/login");   // 이것은 포워드다. 리다이렉트라면 앞에 redirect: 입력해줘야한다.
        } else {
            message = "알 수 없는 오류가 발생했습니다. 관리자에게 문의하세요.";
            System.out.println("message = " + message);
            mv.setViewName("user/signup");
        }

        /* 결과 페이지에서 출력할 메세지를 Model에 추가. */
        mv.addObject("message", message);

        return mv;
    }
}

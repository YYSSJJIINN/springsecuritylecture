package com.ohgiraffers.session.user.model.service;

import com.ohgiraffers.session.user.model.dao.UserMapper;
import com.ohgiraffers.session.user.model.dto.SignupDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;  // 의존성 추가를 위한 작성

    @Autowired
    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Integer regist(SignupDTO newUserDTO) {
        // newUserDTO에서 받은 날것의 비밀번호를 꺼낸다, 암호화(BCryptPasswordEncoder) 한다, 인코딩한다.

        /* 목차. 1. tbl_user 테이블에다가 프론트에서 받은 커맨드 객체newUserDTO로 받은 신규 사용자 정보 INSERT */
        /* 웬만해선 컨트롤러로 빼서 컨트롤러에서 해결하는게 나은 부분이다. */
        System.out.println("암호화 전 PW : " + newUserDTO.getPassword());
        newUserDTO.setPassword(passwordEncoder.encode(newUserDTO.getPassword()));
        System.out.println("암화화 후 PW : " + newUserDTO.getPassword());

        Integer result1 = null;

        try {
            // 다양한 예외처리를 위해 try-catch문으로 감쌌다.
            result1 = userMapper.regist(newUserDTO);    // trycatch문 없이 이 줄만 있어도 된다.
        } catch (DuplicateKeyException e) {
            // 데이터 무결성 위반(중복 키) 발생 시 동작. 여기서 키는 PK다.
            result1 = 0;
            e.printStackTrace();
        } catch (BadSqlGrammarException e) {
            // SQL 문법 오류 발생 시 처리
            result1 = 0;
            e.printStackTrace();
        }

        System.out.println("#1 신규 사용자 정보 삽입 결과 : " + result1);

        /* 목차. 2. tbl_user_role 테이블에 사용자 별 권한 INSERT */
        /* 목차. 2-1. 사용자 PK값 조회 */
        /* 목차. 2-2. tbl_user_role 테이블에 신규 등록된 사용자의 PK와 디폴트 권한(일반사용자) PK인 2를 조합하여 INSERT */

        /* 목차. 3. 위 세 가지 트랜잭션이 모두 성공해야 '회원가입'이라는 비즈니스 로직이 성공했다고 판단. */

        /* 목차. 4. 작업 결과 반환*/

        return null;
    }
}

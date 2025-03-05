package com.ohgiraffers.session.user.model.dto;

/* 데이터베이스에는 컬럼이 사용자 식별코드까지 4개지만 회원가입시 유저가 3개만 입력할 것이므로 이곳엔 식별코드 제외 3개만 작성한다. */
/* 회원가입용 form 데이터를 커맨드 객체로써 한번에 편하게 처리하기 위해 작성 */
public class SignupDTO {

    /* signup.html의 input name의 내용과 변수명이 같아야한다. */
    private String username;    // 사용자 로그인ID = principal 접근주체
    private String password;    // 사용자 로그인 PW
    private String fullName;    // 사용자 이름

    public SignupDTO() {
    }

    public SignupDTO(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "SignupDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

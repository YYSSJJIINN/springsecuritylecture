package com.ohgiraffers.session.user.model.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* DB 스키마를 확인하여 UserDTO를 작성한 후, UserDetails 인터페이스를 구현해 추가 설계를 이어낟간다.
 * UserDetails 인터페이스는 Spring Security에서 사용자의 '핵심 정보'를 지니고 있는 사용자 정보 그 자체라고 볼 수 있다.
 * Spring Security는 보안상의 이유로 해당 인터페이스의 구현체를 직접적으로 사용하지는 않지만,
 * 대신 이를 Authentication 객체에 캡슐화하여 사용한다.
 * 원래 UserDetails 인터페이스는 보안과 전혀 관계 없는 사용자의 이메일주소, 연락처 등과 같은 정보를 저장하고,
 * 이를 참조할 수 있는 위치(location)를 제공하기 위한 편리 목적으로 설계되었다.
 * 덕분에 해당 구현체는 다양한 보안 기능(재정의할 메서드 7개 참고)을 제공하고 확장할 수 있게 되었다.
 * */
public class UserDTO implements UserDetails {
    // 유저 정보가 담긴 UserDTO

    private int userCode;
    private String username;
    private String password;
    private String fullName;
    private List<AuthorityDTO> userAuthorities;     // 권한 리스트이기 때문에 여러개 가질 수도 있기 때문에 리스트 인 것이다. 물론 하나만 가지는 것도 가능.

    /* 설명. 권한 정보를 반환하는 메서드.
     * UsernamePasswordAuthenticationToken에 사용자의 권한 정보를 반환할 때 사용된다.
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        this.userAuthorities.forEach(authority ->
                authorities.add(new SimpleGrantedAuthority(authority.getName())));
        // getName에는 권한명인 ADMIN 또는 USER가 들어간다.

        System.out.println("사용자에게 인가할 권한 객체 : " + authorities);

        return authorities;
    }

    /* 설명. 사용자의 아이디를 반환하는 메서드.
     * UsernamePasswordAuthenticationToken과 사용자의 아이디를 비교할 때 호출된다.
     * */
    @Override
    public String getPassword() {
        System.out.println("UserDTO의 getPassword 호출됨...");
        return this.password;
    }

    /* 설명. 사용자의 아이디를 반환하는 메서드.
     * UsernamePasswordAuthenticationToken과 사용자의 비밀번호를 비교할 때 호출된다.
     * */
    @Override
    public String getUsername() {
        System.out.println("UserDTO의 getUsername 호출됨...");
        return this.username;
    }

    /* 필수는 아닌 부분~ */
//    /* 설명. 계정 만료 여부를 표현하는 메서드로,
//     *  false면 Spring Security가 인증 작업을 수행해주지 않아 해당 계정을 사용할 수 없다.
//     *  (디폴트는 true)
//     * */
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    /* 설명. 잠겨있는 계정을 확인하는 메서드로,
//     *  false면 Spring Security가 인증 작업을 수행해주지 않아 해당 계정을 사용할 수 없다.
//     *  (ex; 비밀번호 반복 실패로 일시적인 계정 lock의 경우 혹은 오랜 기간 비접속으로 휴면 처리)
//     *  (디폴트는 true)
//     * */
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    /* 설명. 탈퇴 계정 여부를 표현하는 메서드로,
//     *  false면 Spring Security가 인증 작업을 수행해주지 않아 해당 계정을 사용할 수 없다.
//     *  (일반적으로 회원 데이터 삭제는 즉시 하는 것이 아닌 일정 기간 보관 후 삭제한다)
//     *  (디폴트는 true)
//     * */
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    /* 설명.
//     *  계정 비활성화 여부로 사용자가 사용할 수 없는 상태를 나타내며,
//     *  false면 Spring Security가 인증 작업을 수행해주지 않아 계정을 사용할 수 없다.
//     *  (디폴트는 true)
//     * */
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }

    public UserDTO() {
    }

    public UserDTO(int userCode, String username, String password, String fullName, List<AuthorityDTO> userAuthorities) {
        this.userCode = userCode;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.userAuthorities = userAuthorities;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public List<AuthorityDTO> getUserAuthorities() {
        return userAuthorities;
    }

    public void setUserAuthorities(List<AuthorityDTO> userAuthorities) {
        this.userAuthorities = userAuthorities;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userCode=" + userCode +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userAuthorities=" + userAuthorities +
                '}';
    }
}
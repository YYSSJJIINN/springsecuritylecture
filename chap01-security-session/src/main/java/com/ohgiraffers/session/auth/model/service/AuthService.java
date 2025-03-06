package com.ohgiraffers.session.auth.model.service;

import com.ohgiraffers.session.user.model.dto.AuthorityDTO;
import com.ohgiraffers.session.user.model.dto.UserDTO;
import com.ohgiraffers.session.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/* UserDetailsService:
 * Security에서 사용자의 아이디를 인증하기 위한 인터페이스다.
 * loadUserByUsername() 메서드를 필수로 구현해야 하며,
 * 로그인 인증 시 해당 메서드에 login 요청시 전달된 사용자의 ID(=username)을 매개변수로하여 DB로부터 조회한다.
 * */
@Service
public class AuthService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    /* Spring Security의 인증 과정에서 사용자 정보를 불러오는 매우 중요한 역할을 수행하는 메서드로,
     * (참고: 정확히는 사용자가 로그인을  시도하는 시점에 DaoAuthenticationProvider가 해당 메서드를 호출함)
     * form-login 방식의 요청 시 전달인자로 받은 username을 기반으로 데이터베이스 또는 다른 저장소에서 사용자 정보를 조회한다.
     * 이 메서드든 사용자의 자격 증명과 권한 정보를 담은 객체를 반환하며 UserDetails를 구현한 구현체가 되어야 한다.
     * 여기서 UserDTO가 UserDetails를 구현한 구현체이기 때문에
     * 데이터베이스에서 조회된 결과를 그대로 UserDTO에 매핑시킨 후, 해당 UserDTO 인스턴스를 반환하면 된다.
     * (다형성으로 인해 UserDTO는 UserDetails 타입이기도 하기 때문.)
     * */
    @Override
    // 사용자가 가져온 아이디 username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("AuthService의 loadUserByUsername 호출됨...");

        /* 목차. 1. tbl_user 테이블로부터 매개변수로 전달된 사용자의 ID로 레코드를 검색 */
        System.out.println("사용자가 입력한 ID 확인 : " + username);

        // 서비스에서 서비스 호출하는게 이상하다면, DAO에 직접 다녀와도 된다.
        // userService는 단순한 UserDTO 방문이고, AuthService는 스프링시큐리티랑 관련있는 방문으로 구별한 것이다.
        UserDTO foundUser = userService.findByUsername(username);
        System.out.println("#1. username 파라미터로 조회된 사용자 : " + foundUser);

        if (Objects.isNull(foundUser)) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다!");
        }

        /* 목차. 2. 검색된 사용자의 PK값을 사용해 tbl_user_role 테이블로부터 해당 사용자가 인가받을 수 있는 모든 권한을 조회 */
        /* UserDTO에서 private List<AuthorityDTO> userAuthorities의 정보를 가져온다. */
        int userCode = foundUser.getUserCode();
        List<AuthorityDTO> authorities = userService.findAllAuthoritiesByUserCode(userCode);
        System.out.println("#2. 해당 사용자가 인가받을 수 있는 권한들 : " + authorities);

        /* 목차. 3. 사용자가 인가받을 모든 권한(List<AuthorityDTO>)을 foundUser에 추가 */
        foundUser.setUserAuthorities(authorities);
        System.out.println("#3. 완성된 UserDetails 타입의 사용자 정보 : " + foundUser);

        /* 목차. 4. 사용자의 모든 인증/인가 정보가 담긴 UserDetails 타입의 데이터를 반환 */
        /* 리턴값을 null로 두면 로그인도 안되고 에러페이지도 안뜨는 상황이 벌어진다. */
        return foundUser;
    }
}

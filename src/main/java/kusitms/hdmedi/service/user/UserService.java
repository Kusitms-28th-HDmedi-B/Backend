package kusitms.hdmedi.service.user;

import kusitms.hdmedi.config.JwtTokenUtil;
import kusitms.hdmedi.domain.user.User;
import kusitms.hdmedi.dto.request.user.LoginRequest;
import kusitms.hdmedi.dto.response.user.InfoResponse;
import kusitms.hdmedi.dto.response.user.LoginResponse;
import kusitms.hdmedi.exception.base.BaseException;
import kusitms.hdmedi.exception.custom.BadRequestException;
import kusitms.hdmedi.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.LoginException;
import java.util.Optional;

import static kusitms.hdmedi.exception.base.ErrorCode.MEMBER_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인
    @Transactional
    public LoginResponse webLogin(LoginRequest req) throws BaseException, LoginException {

        User user = userRepository.findByLoginId(req.getLoginId()) // 아이디 일치 여부 판단
                .orElseThrow(() -> new LoginException("잘못된 아이디 혹은 비밀번호입니다."));

        if (!user.getPassword().equals(req.getPassword())) { // 비밀번호 일치 여부
            throw new LoginException("잘못된 아이디 혹은 비밀번호입니다.");
        }

        String secretKey = "hdmedi-team-b";
        long expireTimeMs = 1000 * 60 * 60;     // Token 유효 시간 = 60분

        String accessToken = JwtTokenUtil.createToken(user.getLoginId(), secretKey, expireTimeMs);

        return new LoginResponse(accessToken);
    }

    // 유저 정보
    public InfoResponse getUserInfo(String loginId) throws BaseException {
        if (loginId == null) {
            throw new BadRequestException("잘못된 요청입니다.");
        }

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if (optionalUser.isEmpty()) {
            throw new BadRequestException(MEMBER_NOT_FOUND);
        }

        User user = optionalUser.get();

        InfoResponse res = InfoResponse.builder()
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .role(user.getRole())
                .build();

        return res;
    }

    public User getLoginUserByLoginId(String loginId) {
        if(loginId == null) return null;

        Optional<User> optionalUser = userRepository.findByLoginId(loginId);
        if(optionalUser.isEmpty()) return null;

        return optionalUser.get();
    }
}
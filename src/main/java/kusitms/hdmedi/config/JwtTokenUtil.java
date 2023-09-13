package kusitms.hdmedi.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

@Slf4j
public class JwtTokenUtil {

    private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";

    private static final String ID_CLAIM = "loginId";

    // JWT 발급
    public static String createToken(String loginId, String key, long expireTimeMs) {

        Date now = new Date();
        return JWT.create()
                .withSubject(ACCESS_TOKEN_SUBJECT)
                .withExpiresAt(new Date(now.getTime() + expireTimeMs)) // 토큰 만료 시간 설정
                .withClaim(ID_CLAIM, loginId)
                .sign(Algorithm.HMAC512(key));
    }

    // Claims 속 loginId 꺼내기
    public static Optional<String> getLoginId(String token, String secretKey) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(token)
                    .getClaim(ID_CLAIM)
                    .asString());
        } catch (Exception e) {
            log.error("토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }

    // 토큰에서 만료 시간을 얻고, 현재 시간과 비교하여 토큰이 만료되었는지 확인
    public static boolean isExpired(String token, String secretKey) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(token);
            return jwt.getExpiresAt().before(new Date());
        } catch (Exception e) {
            return true; // 토큰이 유효하지 않거나 파싱 중 오류가 발생하면 만료된 것으로 처리
        }
    }

    // 토큰을 파싱하여 클레임을 얻는 메소드
    public static Optional<Claim> getClaim(String token, String claimName, String secretKey) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC512(secretKey))
                    .build()
                    .verify(token);
            return Optional.ofNullable(jwt.getClaim(claimName));
        } catch (JWTDecodeException e) {
            return Optional.empty(); // 토큰이 유효하지 않거나 파싱 중 오류가 발생하면 빈 Optional 반환
        }
    }
}

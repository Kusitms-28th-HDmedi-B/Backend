package kusitms.hdmedi.dto.response.user;

import kusitms.hdmedi.domain.user.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InfoResponse {

    private String loginId;
    private String nickname;
    private UserRole role;
    @Builder
    public InfoResponse(String loginId, String nickname, UserRole role) {
        this.loginId = loginId;
        this.nickname = nickname;
        this.role = role;
    }
}
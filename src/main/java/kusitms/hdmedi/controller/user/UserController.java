package kusitms.hdmedi.controller.user;

import kusitms.hdmedi.dto.request.user.LoginRequest;
import kusitms.hdmedi.dto.response.user.InfoResponse;
import kusitms.hdmedi.dto.response.user.LoginResponse;
import kusitms.hdmedi.exception.base.BaseResponse;
import kusitms.hdmedi.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    // 로그인
    @PostMapping("/login")
    public BaseResponse<LoginResponse> webLogin(@RequestBody @Validated LoginRequest req, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            return BaseResponse.onFailure(400, objectError.getDefaultMessage(), null);
        }

        LoginResponse result = userService.webLogin(req);
        return BaseResponse.onSuccess(result);
    }

    // 유저 정보
    @GetMapping("/info")
    public BaseResponse<InfoResponse> userInfo(Authentication auth) {

        InfoResponse result = userService.getUserInfo(auth.getName());
        return BaseResponse.onSuccess(result);
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "관리자 페이지 접근 성공";
    }
}

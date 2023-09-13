package kusitms.hdmedi.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kusitms.hdmedi.dto.request.user.LoginRequest;
import kusitms.hdmedi.dto.response.news.NewsListResponse;
import kusitms.hdmedi.dto.response.news.NewsResponse;
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

@Tag(name = "user", description = "유저 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    // 로그인
    @Operation(description = "로그인하기", summary = "로그인")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = LoginResponse.class)))
    @Parameter(name = "loginId", description = "로그인 ID")
    @Parameter(name = "password", description = "패스워드")
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
    @Operation(description = "유저 정보 조회하기", summary = "유저 정보 조회")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = InfoResponse.class)))
    @Parameter(name = "Bearer 토큰", required = true)
    @GetMapping("/info")
    public BaseResponse<InfoResponse> userInfo(
            @RequestHeader(value="Authorization") Authentication auth) {

        InfoResponse result = userService.getUserInfo(auth.getName());
        return BaseResponse.onSuccess(result);
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "관리자 페이지 접근 성공";
    }
}

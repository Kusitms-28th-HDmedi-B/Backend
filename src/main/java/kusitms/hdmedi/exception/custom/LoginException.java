package kusitms.hdmedi.exception.custom;

import kusitms.hdmedi.exception.base.BaseException;
import kusitms.hdmedi.exception.base.ErrorCode;
import lombok.Getter;

@Getter
public class LoginException extends BaseException {

    private String message;

    public LoginException(String message) {
        super(ErrorCode.REQUEST_ERROR, message);
        this.message = message;
    }

    public LoginException(ErrorCode errorCode) {
        super(errorCode);
    }
}

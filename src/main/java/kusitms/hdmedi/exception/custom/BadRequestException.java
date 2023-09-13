package kusitms.hdmedi.exception.custom;

import kusitms.hdmedi.exception.base.BaseException;
import kusitms.hdmedi.exception.base.ErrorCode;
import lombok.Getter;

@Getter
public class BadRequestException extends BaseException {
    private String message;

    public BadRequestException(String message) {
        super(ErrorCode._BAD_REQUEST, message);
        this.message = message;
    }

    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}

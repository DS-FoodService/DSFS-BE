package com.dsfs.dsfs.global.error.status;

import com.dsfs.dsfs.global.code.BaseErrorCode;
import com.dsfs.dsfs.global.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),

    // 사용자 관련
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER404", "해당하는 유저 정보가 없습니다."),
    WRONG_PASSWORD(HttpStatus.BAD_REQUEST, "USER403", "비밀번호가 틀렸습니다."),
    ALREADY_USER_PASSWORD_SAME(HttpStatus.BAD_REQUEST, "USER408", "사용자의 현재 비밀번호와 동일합니다."),
    ALREADY_USER_ID_EXISTS(HttpStatus.BAD_REQUEST, "USER401", "이미 존재하는 아이디입니다."),
    ALREADY_USER_ID_SAME(HttpStatus.BAD_REQUEST, "USER406", "사용자의 아이디와 동일합니다."),
    ALREADY_USER_NAME_EXISTS(HttpStatus.BAD_REQUEST, "USER405", "이미 존재하는 닉네임입니다."),
    ALREADY_USER_NAME_SAME(HttpStatus.BAD_REQUEST, "USER407", "사용자의 닉네임과 동일합니다."),

    // 즐겨찾기 관련
    ALREADY_SCRAP_EXISTS(HttpStatus.BAD_REQUEST, "SCRAP405", "이미 즐겨찾기되었습니다."),
    SCRAP_NOT_FOUND(HttpStatus.BAD_REQUEST, "SCRAP404", "즐겨찾기가 되어 있지 않습니다.");



    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
    public static ErrorStatus fromCode(String code) {
        for (ErrorStatus status : values()) {
            if (status.name().equals(code)) {
                return status;
            }
        }
        return null;
    }
}

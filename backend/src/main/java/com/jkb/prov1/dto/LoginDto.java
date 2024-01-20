package com.jkb.prov1.dto;

import com.jkb.prov1.common.types.LoginStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDto {
    private boolean status;
    private String message;

    public static LoginDto from(LoginStatus loginStatus) {
        return new LoginDto(loginStatus.getStatus(), loginStatus.getMessage());
    }
}

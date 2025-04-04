package com.example.upgradedschedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    private final String userEmail;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String userPassword;

}

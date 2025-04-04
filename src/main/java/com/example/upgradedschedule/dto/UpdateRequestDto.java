package com.example.upgradedschedule.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String userPassword;

    @Nullable
    private String newUserEmail;

    @Nullable
    private String newPassword;

}

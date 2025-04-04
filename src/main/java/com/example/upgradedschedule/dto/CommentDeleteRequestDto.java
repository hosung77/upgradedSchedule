package com.example.upgradedschedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentDeleteRequestDto {

    @NotBlank(message = "댓글 비밀번호를 입력해주세요.")
    private String commentPassword;

}

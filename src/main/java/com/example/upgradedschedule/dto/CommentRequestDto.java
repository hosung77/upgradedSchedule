package com.example.upgradedschedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String commentContent;

    @NotBlank(message = "댓글 비밀번호를 입력해주세요.")
    @Pattern(regexp = "\\d{4,}", message = "비밀번호는 숫자로만 4자 이상 입력해주세요.")
    private String commentPassword;

}

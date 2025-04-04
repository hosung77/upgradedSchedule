package com.example.upgradedschedule.dto;

import com.example.upgradedschedule.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class EditScheduleRequestDto {

    @Nullable
    private String title;

    @Nullable
    private String content;

    @NotBlank(message = "일정 수정을 위해서 일정 비밀번호를 입력해주세요. 비밀번호는 숫자 4자리 이상입니다.")
    private String schedulePassword;

    @NotNull(message = "날짜를 입력해주세요.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;

}

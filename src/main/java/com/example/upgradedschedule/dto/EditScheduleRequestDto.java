package com.example.upgradedschedule.dto;

import com.example.upgradedschedule.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String schedulePassword;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate scheduleDate;

}

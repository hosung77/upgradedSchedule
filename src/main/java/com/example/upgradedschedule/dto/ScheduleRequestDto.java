package com.example.upgradedschedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleRequestDto {

    private String title;

    private String content;

    private String schedulePassword;

    @JsonFormat(pattern = "yyyy-MM-dd")  // 연-월-일 형식
    private LocalDateTime scheduleDate;
}

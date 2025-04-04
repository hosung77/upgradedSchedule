package com.example.upgradedschedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ScheduleRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Length(max = 10, message = "제목은 10자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    @Length(max = 100, message = "내용은 100자 이내로 입력해주세요.")
    private String content;

    @NotBlank(message = "일정 비밀번호를 입력해주세요.")
    @Pattern(regexp = "\\d{4,}", message = "비밀번호는 숫자로만 4자 이상 입력해주세요.")
    private String schedulePassword;

    @JsonFormat(pattern = "yyyy-MM-dd")  // 연-월-일 형식
    private LocalDate scheduleDate;

}

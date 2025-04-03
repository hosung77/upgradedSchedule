package com.example.upgradedschedule.dto;

import com.example.upgradedschedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponseDto {

    private String userName;

    private Long scheduleId;

    private LocalDate scheduleDate;

    private String title;

    private String content;

    public static ScheduleResponseDto toDto(Schedule sc){
        return new ScheduleResponseDto(sc.getUser().getUserName(), sc.getScheduleId(),sc.getScheduleDate(),sc.getTitle(),sc.getContent());
    }

}

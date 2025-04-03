package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.ScheduleResponseDto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto post(Long userId, String content, LocalDate scheduleDate, String title, String schedulePassword);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findById(Long scheduleId);

    void delete(Long scheduleId);

    ScheduleResponseDto update(Long scheduleId, String title, String content, LocalDate scheduleDate, String schedulePassword);
}

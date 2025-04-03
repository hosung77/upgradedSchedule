package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.ScheduleResponseDto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto post(Long userId, String content, LocalDate scheduleDate, String title, String schedulePassword);

    List<ScheduleResponseDto> findAll(Long userId);

    ScheduleResponseDto findById(Long scheduleId, Long userId);

    void delete(Long scheduleId, Long userId);

    ScheduleResponseDto update(Long scheduleId, String title, String content, LocalDate scheduleDate, String schedulePassword, Long userId);
}

package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.ScheduleResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto post(Long userId, String content, LocalDateTime scheduleDate, String title, String schedulePassword);

    List<ScheduleResponseDto> findAll();

    ScheduleResponseDto findById(Long scheduleId);

    void delete(Long scheduleId);
}

package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.ScheduleResponseDto;
import com.example.upgradedschedule.entity.Schedule;
import com.example.upgradedschedule.entity.User;
import com.example.upgradedschedule.repository.ScheduleRepository;
import com.example.upgradedschedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDto post(Long userId, String content, LocalDateTime scheduleDate, String title, String schedulePassword) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Schedule schedule = new Schedule(title,content,schedulePassword,scheduleDate);
        schedule.setUser(user);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getUser().getUserName(), schedule.getScheduleId(),schedule.getScheduleDate(),schedule.getTitle(),schedule.getContent());
    }

    @Override
    public List<ScheduleResponseDto> findAll() {

       return scheduleRepository.findAll().stream().map(ScheduleResponseDto::toDto).toList();
       
    }

    @Override
    public ScheduleResponseDto findById(Long scheduleId) {

        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));

        return ScheduleResponseDto.toDto(sc);
    }

    @Override
    public void delete(Long scheduleId) {

        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("게시물이 존재하지 않습니다."));

        scheduleRepository.delete(sc);

    }
}

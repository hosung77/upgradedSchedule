package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.ScheduleResponseDto;
import com.example.upgradedschedule.entity.Schedule;
import com.example.upgradedschedule.entity.User;
import com.example.upgradedschedule.exception.CustomException;
import com.example.upgradedschedule.exception.ErrorCode;
import com.example.upgradedschedule.repository.ScheduleRepository;
import com.example.upgradedschedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public ScheduleResponseDto post(Long userId, String content, LocalDate scheduleDate, String title, String schedulePassword) {
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
        Schedule schedule = new Schedule(title,content,schedulePassword,scheduleDate);
        schedule.setUser(user);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getUser().getUserName(), schedule.getScheduleId(),schedule.getScheduleDate(),schedule.getTitle(),schedule.getContent());
    }

    @Override
    @Transactional
    public List<ScheduleResponseDto> findAll(Long userId) {

        return scheduleRepository.findAllByUser_UserId(userId)
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
       
    }

    @Override
    @Transactional
    public ScheduleResponseDto findById(Long scheduleId, Long userId) {

        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!sc.getUser().getUserId().equals(user.getUserId())){
            throw new CustomException(ErrorCode.SCHEDULE_ACCESS_FORBIDDEN);
        }

        return ScheduleResponseDto.toDto(sc);
    }

    @Override
    @Transactional
    public void delete(Long scheduleId, Long userId) {

        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!sc.getUser().getUserId().equals(user.getUserId())){
            throw new CustomException(ErrorCode.ACCOUNT_DELETE_FORBIDDEN);
        }

        scheduleRepository.delete(sc);

    }

    @Override
    @Transactional
    public ScheduleResponseDto update(Long scheduleId, String title, String content, LocalDate scheduleDate, String schedulePassword, Long userId) {

        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new IllegalArgumentException("일정을 찾을 수 없습니다."));
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        if(!sc.getUser().getUserId().equals(user.getUserId())){
            throw new CustomException(ErrorCode.SCHEDULE_FORBIDDEN);
        }

        if (!sc.getSchedulePassword().equals(schedulePassword)){
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        sc.update(title, content, scheduleDate);

        return ScheduleResponseDto.toDto(sc);
    }
}

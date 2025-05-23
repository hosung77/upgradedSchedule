package com.example.upgradedschedule.service;

import com.example.upgradedschedule.config.PasswordEncoder;
import com.example.upgradedschedule.dto.CommentAllResponseDto;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ScheduleResponseDto post(Long userId, String content, LocalDate scheduleDate, String title, String schedulePassword) {
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        String hashedPassword = passwordEncoder.encode(schedulePassword);

        Schedule schedule = new Schedule(title,content,hashedPassword,scheduleDate);

        schedule.setUser(user);

        scheduleRepository.save(schedule);

        // 댓글 리스트를 CommentAllResponseDto 리스트로 변환
        List<CommentAllResponseDto> commentDtos = schedule.getComments().stream()
                .map(CommentAllResponseDto::toDto)
                .collect(Collectors.toList());

        return new ScheduleResponseDto(
                schedule.getUser().getUserName(),
                schedule.getScheduleId(),
                schedule.getScheduleDate(),
                schedule.getTitle(),
                schedule.getContent(),
                commentDtos // 변환된 댓글 리스트를 반환
        );

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

        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!sc.getUser().getUserId().equals(user.getUserId())){
            throw new CustomException(ErrorCode.SCHEDULE_FORBIDDEN);
        }

        if (!passwordEncoder.matches(schedulePassword,sc.getSchedulePassword())){
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        sc.update(title, content, scheduleDate);

        scheduleRepository.save(sc);

        return ScheduleResponseDto.toDto(sc);
    }
}

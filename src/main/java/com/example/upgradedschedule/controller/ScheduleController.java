package com.example.upgradedschedule.controller;

import com.example.upgradedschedule.dto.EditScheduleRequestDto;
import com.example.upgradedschedule.dto.ScheduleRequestDto;
import com.example.upgradedschedule.dto.ScheduleResponseDto;
import com.example.upgradedschedule.entity.Schedule;
import com.example.upgradedschedule.service.ScheduleService;
import com.example.upgradedschedule.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 등록
    @PostMapping("/post")
    public ResponseEntity<ScheduleResponseDto> post(HttpServletRequest request, @Valid @RequestBody ScheduleRequestDto scheduleRequestDto){
        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");
        ScheduleResponseDto sc = scheduleService.post(userId, scheduleRequestDto.getContent(), scheduleRequestDto.getScheduleDate(),scheduleRequestDto.getTitle(),scheduleRequestDto.getSchedulePassword());

        return new ResponseEntity<>(sc, HttpStatus.CREATED);
    }

    // 모든 일정 조회
    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResponseDto>> findAll(HttpServletRequest request){

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        List<ScheduleResponseDto> sc = scheduleService.findAll(userId);

        return new ResponseEntity<>(sc,HttpStatus.OK);
    }

    // 일정 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId, HttpServletRequest request){

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        ScheduleResponseDto sc = scheduleService.findById(scheduleId, userId);

        return new ResponseEntity<>(sc,HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<String> delete(@PathVariable Long scheduleId, HttpServletRequest request){

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        scheduleService.delete(scheduleId, userId);

        return ResponseEntity.ok("삭제를 완료하였습니다.");
    }

    // 일정 수정(원하는 값만 수정 가능)
    @PatchMapping("/edit/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> update(@Valid @RequestBody EditScheduleRequestDto dto, @PathVariable Long scheduleId, HttpServletRequest request){

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        ScheduleResponseDto updatedSchedule = scheduleService.update(scheduleId, dto.getTitle(),dto.getContent(),dto.getScheduleDate(),dto.getSchedulePassword(), userId);

        return new ResponseEntity<>(updatedSchedule,HttpStatus.OK);
    }
}

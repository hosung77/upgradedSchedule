package com.example.upgradedschedule.controller;

import com.example.upgradedschedule.dto.EditScheduleRequestDto;
import com.example.upgradedschedule.dto.ScheduleRequestDto;
import com.example.upgradedschedule.dto.ScheduleResponseDto;
import com.example.upgradedschedule.entity.Schedule;
import com.example.upgradedschedule.service.ScheduleService;
import com.example.upgradedschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final UserService userService;
    private final ScheduleService scheduleService;

    // 일정 등록
    @PostMapping("/post/{userId}")
    public ResponseEntity<ScheduleResponseDto> post(@PathVariable Long userId, @RequestBody ScheduleRequestDto scheduleRequestDto){
        ScheduleResponseDto sc = scheduleService.post(userId, scheduleRequestDto.getContent(), scheduleRequestDto.getScheduleDate(),scheduleRequestDto.getTitle(),scheduleRequestDto.getSchedulePassword());

        return new ResponseEntity<>(sc, HttpStatus.CREATED);
    }

    // 모든 일정 조회
    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResponseDto>> findAll(){

        List<ScheduleResponseDto> sc = scheduleService.findAll();

        return new ResponseEntity<>(sc,HttpStatus.OK);
    }

    // 일정 조회
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long scheduleId){

        ScheduleResponseDto sc = scheduleService.findById(scheduleId);

        return new ResponseEntity<>(sc,HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> delete(@PathVariable Long scheduleId){

        scheduleService.delete(scheduleId);

        return ResponseEntity.ok("삭제를 완료하였습니다.");
    }

    // 일정 수정(원하는 값만 수정 가능)
    @PatchMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> update(@RequestBody EditScheduleRequestDto dto, @PathVariable Long scheduleId){

        ScheduleResponseDto updatedSchedule = scheduleService.update(scheduleId, dto.getTitle(),dto.getContent(),dto.getScheduleDate(),dto.getSchedulePassword());

        return new ResponseEntity<>(updatedSchedule,HttpStatus.OK);
    }
}

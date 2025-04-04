package com.example.upgradedschedule.dto;

import com.example.upgradedschedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private List<CommentAllResponseDto> comments;  // 댓글 목록 추가

    public static ScheduleResponseDto toDto(Schedule sc){
        List<CommentAllResponseDto> commentDtos = sc.getComments().stream()
                .map(CommentAllResponseDto::toDto)  // 댓글들을 Dto로 변환
                .collect(Collectors.toList());
        return new ScheduleResponseDto(
                sc.getUser().getUserName(),
                sc.getScheduleId(),
                sc.getScheduleDate(),
                sc.getTitle(),
                sc.getContent(),
                commentDtos  // 댓글 목록을 포함시킴
        );
    }

}

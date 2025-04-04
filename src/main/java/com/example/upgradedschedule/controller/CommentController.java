package com.example.upgradedschedule.controller;

import com.example.upgradedschedule.dto.*;
import com.example.upgradedschedule.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules/{scheduleId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentResponseDto> post(@Valid @RequestBody CommentRequestDto dto, HttpServletRequest request, @PathVariable Long scheduleId){

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        CommentResponseDto comment = commentService.post(dto.getCommentPassword(), dto.getCommentContent(), userId, scheduleId);

        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }

    // 일정의 댓글 목록 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CommentAllResponseDto>> getComments(@PathVariable Long scheduleId) {

        List<CommentAllResponseDto> comments = commentService.getComments(scheduleId);

        return ResponseEntity.ok(comments);
    }

    // 댓글 수정
    @PutMapping(value = "/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdatedCommentDto> update(@PathVariable Long scheduleId, @PathVariable Long commentId, @Valid @RequestBody CommentRequestDto dto){

        UpdatedCommentDto updatedComment = commentService.update(scheduleId, commentId, dto.getCommentContent(),dto.getCommentPassword());

        return ResponseEntity.ok(updatedComment);

    }


    // 댓글 삭제
    @DeleteMapping(value="/delete/{commentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(@PathVariable Long scheduleId, @PathVariable Long commentId, HttpServletRequest request, @RequestBody CommentDeleteRequestDto dto){

        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        commentService.delete(scheduleId, commentId, userId, dto.getCommentPassword());

        return ResponseEntity.ok("삭제를 완료하였습니다.");
    }



}

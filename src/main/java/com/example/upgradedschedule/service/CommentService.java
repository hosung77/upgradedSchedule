package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.CommentAllResponseDto;
import com.example.upgradedschedule.dto.CommentRequestDto;
import com.example.upgradedschedule.dto.CommentResponseDto;
import com.example.upgradedschedule.dto.UpdatedCommentDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto post(String commentPassword, String commentContent, Long userId, Long scheduleId);

    List<CommentAllResponseDto> getComments(Long scheduleId);

    UpdatedCommentDto update(Long scheduleId, Long commentId, String commentContent, String commentPassword);

    void delete(Long scheduleId, Long commentId, Long userId, String commentPassword);
}

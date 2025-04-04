package com.example.upgradedschedule.service;

import com.example.upgradedschedule.config.PasswordEncoder;
import com.example.upgradedschedule.dto.*;
import com.example.upgradedschedule.entity.Comment;
import com.example.upgradedschedule.entity.Schedule;
import com.example.upgradedschedule.entity.User;
import com.example.upgradedschedule.exception.CustomException;
import com.example.upgradedschedule.exception.ErrorCode;
import com.example.upgradedschedule.repository.CommentRepository;
import com.example.upgradedschedule.repository.ScheduleRepository;
import com.example.upgradedschedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final PasswordEncoder passwordEncoder;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    @Transactional
    public CommentResponseDto post(String commentPassword, String commentContent, Long userId, Long scheduleId) {

        // 유저 조회
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 스케줄 조회
        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        // 비밀번호 암호화
        String hashedPassword = passwordEncoder.encode(commentPassword);

        Comment comment = new Comment(hashedPassword,commentContent,user,sc);

        commentRepository.save(comment);

        return new CommentResponseDto(comment.getCommentContent());

    }

    @Override
    @Transactional
    public List<CommentAllResponseDto> getComments(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .map(schedule -> schedule.getComments()) // 댓글 목록 가져오기
                .stream() // 댓글 목록을 스트림으로 변환
                .flatMap(Collection::stream) // 댓글 리스트를 스트림으로 풀기
                .map(CommentAllResponseDto::toDto) // 각 댓글을 CommentAllResponseDto로 변환
                .collect(Collectors.toList()); // 최종 리스트 반환
    }

    @Override
    public UpdatedCommentDto update(Long scheduleId, Long commentId, String commentContent,String commentPassword) {

        // 스케줄 조회
        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        // 해당 스케줄에서 댓글 ID로 댓글을 찾기
        Comment comment = sc.getComments().stream()
                .filter(c -> c.getCommentId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if(!passwordEncoder.matches(commentPassword,comment.getCommentPassword())){
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        // 댓글 수정
        comment.setCommentContent(commentContent);

        // 수정된 댓글 저장
        commentRepository.save(comment);

        return new UpdatedCommentDto(comment.getCommentContent());
    }

    @Override
    @Transactional
    public void delete(Long scheduleId, Long commentId, Long userId, String commentPassword) {

        // 스케줄 조회
        Schedule sc = scheduleRepository.findById(scheduleId).orElseThrow(()-> new CustomException(ErrorCode.SCHEDULE_NOT_FOUND));

        // 해당 스케줄에서 댓글 ID로 댓글을 찾기
        Comment comment = sc.getComments().stream()
                .filter(c -> c.getCommentId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        if(!passwordEncoder.matches(commentPassword,comment.getCommentPassword())){
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        if(!sc.getUser().getUserId().equals(userId)){
            throw new CustomException(ErrorCode.COMMENT_DELETE_FORBIDDEN_);
        }

        commentRepository.delete(comment);
    }
}

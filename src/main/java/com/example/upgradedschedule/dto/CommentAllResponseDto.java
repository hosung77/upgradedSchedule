package com.example.upgradedschedule.dto;

import com.example.upgradedschedule.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentAllResponseDto {

    private Long commentId;
    private String commentContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public static CommentAllResponseDto toDto(Comment comment) {
        return new CommentAllResponseDto(
                comment.getCommentId(),
                comment.getCommentContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt()
        );
    }

}

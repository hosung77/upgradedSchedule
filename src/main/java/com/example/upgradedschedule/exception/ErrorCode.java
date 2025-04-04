package com.example.upgradedschedule.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 사용자
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"존재하지 않는 유저입니다."),
    PASSWORD_NOT_MATCH(HttpStatus.BAD_REQUEST,"비밀번호가 일치하지 않습니다."),
    WRITER_ALREADY_EXIST(HttpStatus.CONFLICT,"유저가 이미 존재합니다."),

    // 스케줄
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND,"일치하는 스케줄이 없습니다."),
    SCHEDULE_FORBIDDEN(HttpStatus.FORBIDDEN, "본인 일정만 수정 가능합니다."),
    ACCOUNT_DELETE_FORBIDDEN(HttpStatus.FORBIDDEN, "계정 주인만 삭제가 가능합니다."),
    SCHEDULE_ACCESS_FORBIDDEN(HttpStatus.FORBIDDEN, "본인의 스케줄만 확인할 수 있습니다."),

    // 댓글
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND,"일치하는 스케줄이 없습니다."),
    COMMENT_DELETE_FORBIDDEN_(HttpStatus.FORBIDDEN, "댓글 작성자만 삭제가 가능합니다.");

    private final HttpStatus status;
    private final String message;
}
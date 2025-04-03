package com.example.upgradedschedule.dto;

import com.example.upgradedschedule.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {

    private Long userId;

    private String userEmail;

    private String userName;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public UserInfoDto(User user){
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getCreatedAt();
    }

}


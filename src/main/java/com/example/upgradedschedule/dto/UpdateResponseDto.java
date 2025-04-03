package com.example.upgradedschedule.dto;

import com.example.upgradedschedule.entity.User;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateResponseDto {

    private Long userId;

    private String userEmail;

    private String userName;

    public UpdateResponseDto(User user){
        this.userId = user.getUserId();
        this.userEmail = user.getUserEmail();
        this.userName = user.getUserName();
    }
}

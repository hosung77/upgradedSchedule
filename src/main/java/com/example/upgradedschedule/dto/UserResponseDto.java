package com.example.upgradedschedule.dto;


import com.example.upgradedschedule.entity.Schedule;
import com.example.upgradedschedule.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long userId;

    private String userEmail;

    private String userName;


}

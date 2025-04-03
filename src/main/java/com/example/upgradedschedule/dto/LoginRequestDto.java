package com.example.upgradedschedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    private final String userEmail;

    private final String userPassword;

}

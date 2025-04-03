package com.example.upgradedschedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class UserRequestDto {

    private String userEmail;

    private String userName;

    private String userPassword;

}

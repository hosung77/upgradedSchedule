package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.UpdateResponseDto;
import com.example.upgradedschedule.dto.UserInfoDto;
import com.example.upgradedschedule.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface UserService {
    UserResponseDto signUp(String userEmail, String userName, String userPassword);

    UserInfoDto findById(Long userId);

    List<UserInfoDto> findAll();

    UpdateResponseDto update(Long userId, String userEmail, String userPassword, String newUserPassword);

    void delete(Long userId);

    void login(String userEmail, String userPassword, HttpServletRequest request);
}

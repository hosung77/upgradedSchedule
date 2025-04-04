package com.example.upgradedschedule.service;

import com.example.upgradedschedule.config.PasswordEncoder;
import com.example.upgradedschedule.dto.UpdateResponseDto;
import com.example.upgradedschedule.dto.UserInfoDto;
import com.example.upgradedschedule.dto.UserResponseDto;
import com.example.upgradedschedule.entity.User;
import com.example.upgradedschedule.exception.CustomException;
import com.example.upgradedschedule.exception.ErrorCode;
import com.example.upgradedschedule.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto signUp(String userEmail, String userName, String userPassword) {
        String hashedPassword = passwordEncoder.encode(userPassword);

        User user  = new User(userEmail, userName, hashedPassword);

        userRepository.save(user);

        return new UserResponseDto(user.getUserId(),user.getUserEmail(),user.getUserName());
    }

    @Override
    @Transactional
    public UserInfoDto findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        return new UserInfoDto(user);
    }

    @Override
    public List<UserInfoDto> findAll() {
        return userRepository.findAll().stream().map(UserInfoDto::new).toList();
    }

    @Override
    @Transactional
    public UpdateResponseDto update(Long userId, String userEmail, String userPassword, String newUserPassword) {
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        if(!passwordEncoder.matches(userPassword,user.getUserPassword())){
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        user.updateUserInfo(userEmail,newUserPassword);

        return new UpdateResponseDto(user);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);
    }

    @Override
    public void login(String userEmail, String userPassword, HttpServletRequest request) {

        // 이메일로 사용자 조회
        User user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호 검증 (현재 String 비교)
        if (!passwordEncoder.matches(userPassword,user.getUserPassword())) {
            throw new CustomException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        // 세션 생성 및 사용자 ID 저장
        HttpSession session = request.getSession(true);
        session.setAttribute("sessionKey", user.getUserId());

    }

}

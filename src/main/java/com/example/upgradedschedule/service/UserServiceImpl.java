package com.example.upgradedschedule.service;

import com.example.upgradedschedule.dto.UpdateResponseDto;
import com.example.upgradedschedule.dto.UserInfoDto;
import com.example.upgradedschedule.dto.UserResponseDto;
import com.example.upgradedschedule.entity.User;
import com.example.upgradedschedule.repository.ScheduleRepository;
import com.example.upgradedschedule.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Override
    public UserResponseDto signUp(String userEmail, String userName, String userPassword) {
        User user  = new User(userEmail, userName, userPassword);

        userRepository.save(user);

        return new UserResponseDto(user.getUserId(),user.getUserEmail(),user.getUserName());
    }

    @Override
    @Transactional
    public UserInfoDto findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return new UserInfoDto(user);
    }

    @Override
    public List<UserInfoDto> findAll() {
        return userRepository.findAll().stream().map(UserInfoDto::new).toList();
    }

    @Override
    @Transactional
    public UpdateResponseDto update(Long userId, String userEmail, String userPassword, String newUserPassword) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        if(!user.getUserPassword().equals(userPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다.");
        }

        user.updateUserInfo(userEmail,newUserPassword);

        return new UpdateResponseDto(user);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new IllegalArgumentException("존재하지 않는 유저입니다."));

        userRepository.delete(user);
    }

}

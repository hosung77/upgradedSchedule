package com.example.upgradedschedule.controller;

import com.example.upgradedschedule.dto.*;
import com.example.upgradedschedule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원 등록
    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRequestDto ud){

        UserResponseDto dto = userService.signUp(ud.getUserEmail(),ud.getUserName(),ud.getUserPassword());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // 특정 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDto> findUserById(@PathVariable Long userId){

        UserInfoDto userInfo = userService.findById(userId);

        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    // 모든 유저 조회
    @GetMapping("/all")
    public ResponseEntity<List<UserInfoDto>> findAll(){

        List<UserInfoDto> users = userService.findAll();

        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    // 유저 정보 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<UpdateResponseDto> update(@PathVariable Long userId, @RequestBody UpdateRequestDto up){

        UpdateResponseDto dto = userService.update(userId, up.getNewUserEmail(), up.getUserPassword(),up.getNewPassword());

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> delete(@PathVariable Long userId){

        userService.delete(userId);

        return ResponseEntity.ok("삭제를 완료하였습니다.");
    }
}

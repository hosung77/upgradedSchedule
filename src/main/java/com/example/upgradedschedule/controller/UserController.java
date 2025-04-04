package com.example.upgradedschedule.controller;

import com.example.upgradedschedule.dto.*;
import com.example.upgradedschedule.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto ud){

        UserResponseDto dto = userService.signUp(ud.getUserEmail(),ud.getUserName(),ud.getUserPassword());

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> Login(@Valid @RequestBody LoginRequestDto dto, HttpServletRequest request){

        userService.login(dto.getUserEmail(), dto.getUserPassword(), request);

        return ResponseEntity.ok("로그인에 성공하셨습니다.");
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); //세션 삭제 (로그아웃 처리)
        }

        return ResponseEntity.ok("로그아웃 되었습니다.");
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
    @PatchMapping("/edit")
    public ResponseEntity<UpdateResponseDto> update(HttpServletRequest request, @Valid @RequestBody UpdateRequestDto up){

        // false를 넣은 이유는 새로운 세션을 만들지 않기 위해서이다.
        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        UpdateResponseDto dto = userService.update(userId, up.getNewUserEmail(), up.getUserPassword(),up.getNewPassword());

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(HttpServletRequest request){

        // false를 넣은 이유는 새로운 세션을 만들지 않기 위해서이다.
        HttpSession session = request.getSession(false);

        Long userId = (Long) session.getAttribute("sessionKey");

        userService.delete(userId);

        return ResponseEntity.ok("삭제를 완료하였습니다.");

    }
}

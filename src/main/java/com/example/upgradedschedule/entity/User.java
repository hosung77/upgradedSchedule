package com.example.upgradedschedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Slf4j
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    public User(String userEmail, String userName, String userPassword){
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public void updateUserInfo(String userNewEmail, String userNewPassword) {
        if (userNewEmail != null) {
            this.userEmail = userNewEmail;
        }
        if (userNewPassword != null) {
            this.userPassword = userNewPassword;
        }
    }

    public void checkUser(String userEmail, String userPassword){
        if(!(this.userEmail.equals(userEmail) && this.userPassword.equals(userPassword))){
            throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
        } else {
                log.info("사용자 정보가 일치합니다.");
        }
    }

}

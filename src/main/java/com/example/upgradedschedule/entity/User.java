package com.example.upgradedschedule.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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

}

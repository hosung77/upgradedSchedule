package com.example.upgradedschedule.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String schedulePassword;

    @Column(nullable = false)
    private LocalDateTime scheduleDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public Schedule(String title, String content,String schedulePassword,LocalDateTime scheduleDate){
        this.title = title;
        this.content = content;
        this.schedulePassword = schedulePassword;
        this.scheduleDate = scheduleDate;
    }
}

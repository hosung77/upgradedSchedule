package com.example.upgradedschedule.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate scheduleDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public void setUser(User user){
        this.user = user;
    }

    public Schedule(String title, String content,String schedulePassword, LocalDate scheduleDate){
        this.title = title;
        this.content = content;
        this.schedulePassword = schedulePassword;
        this.scheduleDate = scheduleDate;
    }

    public void update(String title, String content, LocalDate scheduleDate) {
        if (title != null) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
        if (scheduleDate != null){
            this.scheduleDate = scheduleDate;
        }
    }

}

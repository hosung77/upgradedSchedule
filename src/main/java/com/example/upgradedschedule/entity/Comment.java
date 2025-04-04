package com.example.upgradedschedule.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Entity
@Table(name = "comment")
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String commentPassword;

    @Column(nullable = false)
    private String commentContent;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="schedule_id")
    private Schedule schedule;

    public Comment(String commentPassword, String commentContent, User user, Schedule schedule){
        this.commentPassword = commentPassword;
        this.commentContent = commentContent;
        this.user = user;
        this.schedule = schedule;
    }

}


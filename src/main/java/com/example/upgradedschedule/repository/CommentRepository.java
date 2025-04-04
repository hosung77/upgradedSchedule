package com.example.upgradedschedule.repository;

import com.example.upgradedschedule.entity.Comment;
import com.example.upgradedschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

package com.example.upgradedschedule.repository;

import com.example.upgradedschedule.dto.UserResponseDto;
import com.example.upgradedschedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserEmail(String userEmail);

}

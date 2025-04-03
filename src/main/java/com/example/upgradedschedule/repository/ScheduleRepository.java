package com.example.upgradedschedule.repository;


import com.example.upgradedschedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findAllByUser_UserId(Long userId);

}

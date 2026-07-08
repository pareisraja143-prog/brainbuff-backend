package com.example.brainbuff.repository;

import com.example.brainbuff.entity.ActivitySession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivitySessionRepository extends JpaRepository<ActivitySession, Long> {

    List<ActivitySession> findByTraineeId(Long traineeId);
}

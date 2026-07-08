package com.example.brainbuff.service;

import com.example.brainbuff.dto.SessionResultRequestDto;
import com.example.brainbuff.entity.ActivitySession;
import com.example.brainbuff.entity.SystemUser;
import com.example.brainbuff.exception.ResourceNotFoundException;
import com.example.brainbuff.repository.ActivitySessionRepository;
import com.example.brainbuff.repository.SystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    private final ActivitySessionRepository activitySessionRepository;
    private final SystemUserRepository systemUserRepository;

    public SessionService(ActivitySessionRepository activitySessionRepository,
                           SystemUserRepository systemUserRepository) {
        this.activitySessionRepository = activitySessionRepository;
        this.systemUserRepository = systemUserRepository;
    }

    public void submitSessionResult(SessionResultRequestDto request, String username) {
        SystemUser trainee = systemUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        ActivitySession session = new ActivitySession();
        session.setTraineeId(trainee.getId());
        session.setExerciseId(request.getExerciseId());
        session.setRawScore(request.getRawScore());
        session.setCompletionTimeMs(request.getCompletionTimeMs());
        session.setAccuracyRate(request.getAccuracyRate());

        activitySessionRepository.save(session);
    }

    public List<ActivitySession> getHistory(String username) {
        SystemUser trainee = systemUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));

        return activitySessionRepository.findByTraineeId(trainee.getId());
    }
}

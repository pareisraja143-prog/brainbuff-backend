package com.example.brainbuff.service;

import com.example.brainbuff.dto.DashboardStatsDto;
import com.example.brainbuff.repository.ActivitySessionRepository;
import com.example.brainbuff.repository.CognitiveExerciseRepository;
import com.example.brainbuff.repository.MemoryDeckRepository;
import com.example.brainbuff.repository.SystemUserRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final SystemUserRepository systemUserRepository;
    private final MemoryDeckRepository memoryDeckRepository;
    private final CognitiveExerciseRepository cognitiveExerciseRepository;
    private final ActivitySessionRepository activitySessionRepository;

    public DashboardService(SystemUserRepository systemUserRepository,
                             MemoryDeckRepository memoryDeckRepository,
                             CognitiveExerciseRepository cognitiveExerciseRepository,
                             ActivitySessionRepository activitySessionRepository) {
        this.systemUserRepository = systemUserRepository;
        this.memoryDeckRepository = memoryDeckRepository;
        this.cognitiveExerciseRepository = cognitiveExerciseRepository;
        this.activitySessionRepository = activitySessionRepository;
    }

    public DashboardStatsDto getStats() {
        long totalUsers = systemUserRepository.count();
        long totalDecks = memoryDeckRepository.count();
        long totalExercises = cognitiveExerciseRepository.count();
        long totalSessions = activitySessionRepository.count();

        return new DashboardStatsDto(totalUsers, totalDecks, totalExercises, totalSessions);
    }
}

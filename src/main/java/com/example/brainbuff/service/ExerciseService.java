package com.example.brainbuff.service;

import com.example.brainbuff.dto.ExerciseRequestDto;
import com.example.brainbuff.entity.CognitiveExercise;
import com.example.brainbuff.exception.ResourceNotFoundException;
import com.example.brainbuff.repository.CognitiveExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final CognitiveExerciseRepository cognitiveExerciseRepository;

    public ExerciseService(CognitiveExerciseRepository cognitiveExerciseRepository) {
        this.cognitiveExerciseRepository = cognitiveExerciseRepository;
    }

    public CognitiveExercise addExercise(ExerciseRequestDto request) {
        CognitiveExercise exercise = new CognitiveExercise();
        exercise.setTitle(request.getTitle());
        exercise.setDescription(request.getDescription());
        exercise.setExerciseType(request.getExerciseType());
        exercise.setDifficultyLevel(request.getDifficultyLevel());
        exercise.setInstructions(request.getInstructions());

        return cognitiveExerciseRepository.save(exercise);
    }

    public List<CognitiveExercise> getAllExercises() {
        return cognitiveExerciseRepository.findAll();
    }

    public CognitiveExercise getExerciseById(Long id) {
        return cognitiveExerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CognitiveExercise not found with id: " + id));
    }

    public CognitiveExercise updateExercise(Long id, ExerciseRequestDto request) {
        CognitiveExercise exercise = getExerciseById(id);
        exercise.setTitle(request.getTitle());
        exercise.setDescription(request.getDescription());
        exercise.setExerciseType(request.getExerciseType());
        exercise.setDifficultyLevel(request.getDifficultyLevel());
        exercise.setInstructions(request.getInstructions());
        return cognitiveExerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id) {
        CognitiveExercise exercise = getExerciseById(id);
        cognitiveExerciseRepository.delete(exercise);
    }
}

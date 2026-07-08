package com.example.brainbuff.controller;

import com.example.brainbuff.dto.ExerciseRequestDto;
import com.example.brainbuff.entity.CognitiveExercise;
import com.example.brainbuff.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@CrossOrigin
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public ResponseEntity<List<CognitiveExercise>> getAllExercises() {
        List<CognitiveExercise> exercises = exerciseService.getAllExercises();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SYSTEM_ARCHITECT', 'NEURO_COACH')")
    public ResponseEntity<CognitiveExercise> addExercise(@RequestBody ExerciseRequestDto request) {
        CognitiveExercise exercise = exerciseService.addExercise(request);
        return new ResponseEntity<>(exercise, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CognitiveExercise> getExerciseById(@PathVariable Long id) {
        CognitiveExercise exercise = exerciseService.getExerciseById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ARCHITECT', 'NEURO_COACH')")
    public ResponseEntity<CognitiveExercise> updateExercise(@PathVariable Long id,
                                                              @RequestBody ExerciseRequestDto request) {
        CognitiveExercise exercise = exerciseService.updateExercise(id, request);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ARCHITECT', 'NEURO_COACH')")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>("Exercise deleted successfully.", HttpStatus.OK);
    }
}

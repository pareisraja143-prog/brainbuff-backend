package com.example.brainbuff.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cognitive_exercises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CognitiveExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(name = "exercise_type")
    private String exerciseType;

    @Column(name = "difficulty_level")
    private Integer difficultyLevel;

    @Column(length = 1000)
    private String instructions;
}

package com.example.brainbuff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRequestDto {

    private String title;
    private String description;
    private String exerciseType;
    private Integer difficultyLevel;
    private String instructions;
}

package com.example.brainbuff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResultRequestDto {

    private Long exerciseId;
    private Integer rawScore;
    private Long completionTimeMs;
    private Double accuracyRate;
}

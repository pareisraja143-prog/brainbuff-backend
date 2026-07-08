package com.example.brainbuff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardStatsDto {

    private long totalUsers;
    private long totalDecks;
    private long totalExercises;
    private long totalSessions;
}

package com.example.brainbuff.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "activity_sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivitySession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "trainee_id", nullable = false)
    private Long traineeId;

    @Column(name = "exercise_id", nullable = false)
    private Long exerciseId;

    @Column(name = "raw_score")
    private Integer rawScore;

    @Column(name = "completion_time_ms")
    private Long completionTimeMs;

    @Column(name = "accuracy_rate")
    private Double accuracyRate;

    private LocalDateTime timestamp;

    @PrePersist
    public void prePersist() {
        this.timestamp = LocalDateTime.now();
    }
}

package com.example.brainbuff.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "memory_decks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoryDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String category;

    @Column(name = "card_count")
    private Integer cardCount;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
}

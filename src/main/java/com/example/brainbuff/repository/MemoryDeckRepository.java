package com.example.brainbuff.repository;

import com.example.brainbuff.entity.MemoryDeck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryDeckRepository extends JpaRepository<MemoryDeck, Long> {

    List<MemoryDeck> findByOwnerId(Long ownerId);
}

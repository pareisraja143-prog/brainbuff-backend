package com.example.brainbuff.service;

import com.example.brainbuff.dto.DeckRequestDto;
import com.example.brainbuff.entity.MemoryDeck;
import com.example.brainbuff.entity.SystemUser;
import com.example.brainbuff.exception.ResourceNotFoundException;
import com.example.brainbuff.repository.MemoryDeckRepository;
import com.example.brainbuff.repository.SystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryDeckService {

    private final MemoryDeckRepository memoryDeckRepository;
    private final SystemUserRepository systemUserRepository;

    public MemoryDeckService(MemoryDeckRepository memoryDeckRepository,
                              SystemUserRepository systemUserRepository) {
        this.memoryDeckRepository = memoryDeckRepository;
        this.systemUserRepository = systemUserRepository;
    }

    public MemoryDeck createDeck(DeckRequestDto request, String username) {
        SystemUser owner = getUserByUsername(username);

        MemoryDeck deck = new MemoryDeck();
        deck.setTitle(request.getTitle());
        deck.setCategory(request.getCategory());
        deck.setCardCount(request.getCardCount());
        deck.setOwnerId(owner.getId());

        return memoryDeckRepository.save(deck);
    }

    public List<MemoryDeck> getMyDecks(String username) {
        SystemUser owner = getUserByUsername(username);
        return memoryDeckRepository.findByOwnerId(owner.getId());
    }

    public MemoryDeck getDeckById(Long id) {
        return memoryDeckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MemoryDeck not found with id: " + id));
    }

    public MemoryDeck updateDeck(Long id, DeckRequestDto request) {
        MemoryDeck deck = getDeckById(id);
        deck.setTitle(request.getTitle());
        deck.setCategory(request.getCategory());
        deck.setCardCount(request.getCardCount());
        return memoryDeckRepository.save(deck);
    }

    public void deleteDeck(Long id) {
        MemoryDeck deck = getDeckById(id);
        memoryDeckRepository.delete(deck);
    }

    private SystemUser getUserByUsername(String username) {
        return systemUserRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }
}

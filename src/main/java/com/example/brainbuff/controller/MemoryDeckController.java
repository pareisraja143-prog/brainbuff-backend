package com.example.brainbuff.controller;

import com.example.brainbuff.dto.DeckRequestDto;
import com.example.brainbuff.entity.MemoryDeck;
import com.example.brainbuff.service.MemoryDeckService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
@CrossOrigin
public class MemoryDeckController {

    private final MemoryDeckService memoryDeckService;

    public MemoryDeckController(MemoryDeckService memoryDeckService) {
        this.memoryDeckService = memoryDeckService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('TRAINEE', 'NEURO_COACH', 'SYSTEM_ARCHITECT')")
    public ResponseEntity<List<MemoryDeck>> getMyDecks(Authentication authentication) {
        List<MemoryDeck> decks = memoryDeckService.getMyDecks(authentication.getName());
        return new ResponseEntity<>(decks, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('TRAINEE', 'NEURO_COACH', 'SYSTEM_ARCHITECT')")
    public ResponseEntity<MemoryDeck> createDeck(@RequestBody DeckRequestDto request,
                                                  Authentication authentication) {
        MemoryDeck deck = memoryDeckService.createDeck(request, authentication.getName());
        return new ResponseEntity<>(deck, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('TRAINEE', 'NEURO_COACH', 'SYSTEM_ARCHITECT')")
    public ResponseEntity<MemoryDeck> getDeckById(@PathVariable Long id) {
        MemoryDeck deck = memoryDeckService.getDeckById(id);
        return new ResponseEntity<>(deck, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('TRAINEE', 'NEURO_COACH', 'SYSTEM_ARCHITECT')")
    public ResponseEntity<MemoryDeck> updateDeck(@PathVariable Long id, @RequestBody DeckRequestDto request) {
        MemoryDeck deck = memoryDeckService.updateDeck(id, request);
        return new ResponseEntity<>(deck, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('TRAINEE', 'NEURO_COACH', 'SYSTEM_ARCHITECT')")
    public ResponseEntity<String> deleteDeck(@PathVariable Long id) {
        memoryDeckService.deleteDeck(id);
        return new ResponseEntity<>("MemoryDeck deleted successfully.", HttpStatus.OK);
    }
}

package com.example.brainbuff.controller;

import com.example.brainbuff.dto.SessionResultRequestDto;
import com.example.brainbuff.entity.ActivitySession;
import com.example.brainbuff.service.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@CrossOrigin
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/submit")
    @PreAuthorize("hasRole('TRAINEE')")
    public ResponseEntity<String> submitSessionResult(@RequestBody SessionResultRequestDto request,
                                                        Authentication authentication) {
        sessionService.submitSessionResult(request, authentication.getName());
        return new ResponseEntity<>("Result submitted successfully.", HttpStatus.OK);
    }

    @GetMapping("/history")
    @PreAuthorize("hasRole('TRAINEE')")
    public ResponseEntity<List<ActivitySession>> getHistory(Authentication authentication) {
        List<ActivitySession> history = sessionService.getHistory(authentication.getName());
        return new ResponseEntity<>(history, HttpStatus.OK);
    }
}

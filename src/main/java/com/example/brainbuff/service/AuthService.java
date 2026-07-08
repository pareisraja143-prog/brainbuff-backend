package com.example.brainbuff.service;

import com.example.brainbuff.dto.AuthRequestDto;
import com.example.brainbuff.dto.AuthResponseDto;
import com.example.brainbuff.dto.RegisterDto;
import com.example.brainbuff.entity.SystemUser;
import com.example.brainbuff.exception.ResourceNotFoundException;
import com.example.brainbuff.repository.SystemUserRepository;
import com.example.brainbuff.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(SystemUserRepository systemUserRepository,
                        PasswordEncoder passwordEncoder,
                        JwtUtil jwtUtil,
                        AuthenticationManager authenticationManager) {
        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDto register(RegisterDto request) {
        SystemUser user = new SystemUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        SystemUser savedUser = systemUserRepository.save(user);

        UserDetails userDetails = new User(
                savedUser.getUsername(),
                savedUser.getPassword(),
                List.of(() -> "ROLE_" + savedUser.getRole().name())
        );

        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponseDto(token, savedUser.getUsername(), savedUser.getRole(), savedUser.getId());
    }

    public AuthResponseDto login(AuthRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SystemUser user = systemUserRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + request.getUsername()));

        UserDetails userDetails = new User(
                user.getUsername(),
                user.getPassword(),
                List.of(() -> "ROLE_" + user.getRole().name())
        );

        String token = jwtUtil.generateToken(userDetails);

        return new AuthResponseDto(token, user.getUsername(), user.getRole(), user.getId());
    }
}

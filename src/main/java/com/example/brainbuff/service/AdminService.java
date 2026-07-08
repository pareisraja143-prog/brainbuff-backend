package com.example.brainbuff.service;

import com.example.brainbuff.dto.RoleUpdateDto;
import com.example.brainbuff.entity.SystemUser;
import com.example.brainbuff.exception.ResourceNotFoundException;
import com.example.brainbuff.repository.SystemUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final SystemUserRepository systemUserRepository;

    public AdminService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public List<SystemUser> getAllUsers() {
        return systemUserRepository.findAll();
    }

    public SystemUser changeUserRole(Long userId, RoleUpdateDto request) {
        SystemUser user = systemUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setRole(request.getRole());
        return systemUserRepository.save(user);
    }
}

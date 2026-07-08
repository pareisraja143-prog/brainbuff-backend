package com.example.brainbuff.controller;

import com.example.brainbuff.dto.RoleUpdateDto;
import com.example.brainbuff.entity.SystemUser;
import com.example.brainbuff.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('SYSTEM_ARCHITECT')")
    public ResponseEntity<List<SystemUser>> getAllUsers() {
        List<SystemUser> users = adminService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/users/{id}/role")
    @PreAuthorize("hasRole('SYSTEM_ARCHITECT')")
    public ResponseEntity<SystemUser> changeUserRole(@PathVariable Long id, @RequestBody RoleUpdateDto request) {
        SystemUser updatedUser = adminService.changeUserRole(id, request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}

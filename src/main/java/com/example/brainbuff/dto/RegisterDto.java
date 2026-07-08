package com.example.brainbuff.dto;

import com.example.brainbuff.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {

    private String username;
    private String email;
    private String password;
    private UserRole role;
}

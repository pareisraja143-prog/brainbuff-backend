package com.example.brainbuff.dto;

import com.example.brainbuff.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {

    private String token;
    private String username;
    private UserRole role;
    private Long userId;
}

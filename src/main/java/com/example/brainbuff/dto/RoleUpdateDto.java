package com.example.brainbuff.dto;

import com.example.brainbuff.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateDto {

    private UserRole role;
}

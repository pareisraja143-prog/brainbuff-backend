package com.example.brainbuff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeckRequestDto {

    private String title;
    private String category;
    private Integer cardCount;
}

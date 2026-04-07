package com.bridgelabz.fundoo_part1.dto;

import lombok.*;

@AllArgsConstructor
@Data
public class LoginResponseDto {
    private String token;
    private String message;
}
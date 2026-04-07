package com.bridgelabz.fundoo_part1.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
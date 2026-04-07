package com.bridgelabz.fundoo_part1.dto;
import jakarta.validation.constraints.*;
import lombok.*;
@Data
public class UserRegisterRequestDto {

    @NotBlank
    private String firstName;

    @Email
    private String email;

    @NotBlank
    private String password;
}
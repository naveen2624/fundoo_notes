package com.bridgelabz.fundoo_part1.controller;
import com.bridgelabz.fundoo_part1.dto.*;
import com.bridgelabz.fundoo_part1.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;
import lombok.*;
import org.springframework.http.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterRequestDto dto) {
        userService.register(dto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }
}
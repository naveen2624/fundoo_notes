package com.bridgelabz.fundoo_part1.service;

import com.bridgelabz.fundoo_part1.dto.LoginRequestDto;
import com.bridgelabz.fundoo_part1.dto.LoginResponseDto;
import com.bridgelabz.fundoo_part1.dto.UserRegisterRequestDto;
import com.bridgelabz.fundoo_part1.entity.User;
import com.bridgelabz.fundoo_part1.repository.UserRepository;
import com.bridgelabz.fundoo_part1.util.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtil tokenUtil;

    public void register(UserRegisterRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = tokenUtil.generateToken(user.getId());

        return new LoginResponseDto(token, "Login successful");
    }
}
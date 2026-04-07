package com.bridgelabz.fundoo_part1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    @Column(unique = true)
    private String email;

    private String password;
}
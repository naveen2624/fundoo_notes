package com.bridgelabz.fundoo_part1.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    private boolean pinned = false;
    private boolean archived = false;
    private boolean trashed = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

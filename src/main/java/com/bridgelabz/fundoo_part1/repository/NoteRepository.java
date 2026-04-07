package com.bridgelabz.fundoo_part1.repository;

import com.bridgelabz.fundoo_part1.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUserId(Long userId);

    List<Note> findByUserIdAndTrashedFalse(Long userId);
}
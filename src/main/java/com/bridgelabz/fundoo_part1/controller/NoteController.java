package com.bridgelabz.fundoo_part1.controller;

import com.bridgelabz.fundoo_part1.dto.NoteRequestDto;
import com.bridgelabz.fundoo_part1.dto.NoteResponseDto;
import com.bridgelabz.fundoo_part1.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public ResponseEntity<NoteResponseDto> createNote(
            @RequestBody NoteRequestDto dto,
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(noteService.createNote(dto, userId));
    }

    @GetMapping
    public ResponseEntity<List<NoteResponseDto>> getAllNotes(
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(noteService.getAllNotes(userId));
    }

    @PatchMapping("/{id}/pin")
    public ResponseEntity<NoteResponseDto> pinNote(
            @PathVariable Long id,
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(noteService.pinNote(id, userId));
    }

    @PatchMapping("/{id}/archive")
    public ResponseEntity<NoteResponseDto> archiveNote(
            @PathVariable Long id,
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        return ResponseEntity.ok(noteService.archiveNote(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNote(
            @PathVariable Long id,
            HttpServletRequest request) {

        Long userId = (Long) request.getAttribute("userId");

        noteService.deleteNote(id, userId);
        return ResponseEntity.ok("Note moved to trash");
    }
}
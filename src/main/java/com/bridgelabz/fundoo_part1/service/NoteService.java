package com.bridgelabz.fundoo_part1.service;

import com.bridgelabz.fundoo_part1.dto.NoteRequestDto;
import com.bridgelabz.fundoo_part1.dto.NoteResponseDto;

import java.util.List;

public interface NoteService {

    NoteResponseDto createNote(NoteRequestDto dto, Long userId);

    List<NoteResponseDto> getAllNotes(Long userId);

    NoteResponseDto pinNote(Long noteId, Long userId);

    NoteResponseDto archiveNote(Long noteId, Long userId);

    void deleteNote(Long noteId, Long userId);
}
package com.bridgelabz.fundoo_part1.service;

import com.bridgelabz.fundoo_part1.dto.NoteRequestDto;
import com.bridgelabz.fundoo_part1.dto.NoteResponseDto;
import com.bridgelabz.fundoo_part1.entity.Note;
import com.bridgelabz.fundoo_part1.entity.User;
import com.bridgelabz.fundoo_part1.repository.NoteRepository;
import com.bridgelabz.fundoo_part1.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @Override
    public NoteResponseDto createNote(NoteRequestDto dto, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setDescription(dto.getDescription());
        note.setUser(user);

        Note saved = noteRepository.save(note);

        return mapToDto(saved);
    }

    @Override
    public List<NoteResponseDto> getAllNotes(Long userId) {

        return noteRepository.findByUserIdAndTrashedFalse(userId)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public NoteResponseDto pinNote(Long noteId, Long userId) {

        Note note = getUserNote(noteId, userId);
        note.setPinned(!note.isPinned());

        return mapToDto(noteRepository.save(note));
    }

    @Override
    public NoteResponseDto archiveNote(Long noteId, Long userId) {

        Note note = getUserNote(noteId, userId);
        note.setArchived(!note.isArchived());

        return mapToDto(noteRepository.save(note));
    }

    @Override
    public void deleteNote(Long noteId, Long userId) {

        Note note = getUserNote(noteId, userId);
        note.setTrashed(true);
        noteRepository.save(note);
    }

    private Note getUserNote(Long noteId, Long userId) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));

        if (!note.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        return note;
    }

    private NoteResponseDto mapToDto(Note note) {
        return new NoteResponseDto(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.isPinned(),
                note.isArchived(),
                note.isTrashed()
        );
    }
}
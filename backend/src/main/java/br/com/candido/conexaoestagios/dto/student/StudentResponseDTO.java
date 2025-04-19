package br.com.candido.conexaoestagios.dto.student;

import br.com.candido.conexaoestagios.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record StudentResponseDTO(
        Long id,
        String name,
        String username,
        String linkedin,
        String email,
        String course,
        String institution,
        List<String> skills,
        String areaOfInterest,
        String phoneNumber,
        String city,
        String state,
        Role role,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime registrationDate) {
}

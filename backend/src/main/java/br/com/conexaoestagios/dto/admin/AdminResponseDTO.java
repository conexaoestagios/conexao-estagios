package br.com.conexaoestagios.dto.admin;

import br.com.conexaoestagios.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record AdminResponseDTO(
        Long id,
        String name,
        String username,
        String email,
        String phoneNumber,
        String city,
        String state,
        Role role,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime registrationDate) {
}

package br.com.candido.conexaoestagios.dto.company;

import br.com.candido.conexaoestagios.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record CompanyResponseDTO(
        Long id,
        String name,
        String username,
        String legalName,
        String linkedin,
        String email,
        String sector,
        String phoneNumber,
        String city,
        String state,
        Role role,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime registrationDate) {
}

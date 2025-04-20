package br.com.conexaoestagios.dto.user;

import br.com.conexaoestagios.dto.address.AddressResponseDTO;
import br.com.conexaoestagios.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String username,
        String linkedin,
        String email,
        String phoneNumber,
        Role role,
       AddressResponseDTO addressResponseDTO,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime registrationDate
) {
}

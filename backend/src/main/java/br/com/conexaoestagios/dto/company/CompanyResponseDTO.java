package br.com.conexaoestagios.dto.company;

import br.com.conexaoestagios.dto.user.UserResponseDTO;

public record CompanyResponseDTO(
        Long id,
        String legalName,
        String sector,
        UserResponseDTO userResponseDto
) {
}

package br.com.conexaoestagios.dto.admin;

import br.com.conexaoestagios.dto.user.UserResponseDTO;

public record AdminResponseDTO(
        Long id,
      UserResponseDTO userResponseDTO
) {
}

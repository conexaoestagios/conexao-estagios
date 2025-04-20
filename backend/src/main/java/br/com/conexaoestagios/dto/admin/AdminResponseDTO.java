package br.com.conexaoestagios.dto.admin;

import br.com.conexaoestagios.entities.users.User;

public record AdminResponseDTO(
       Long id,
        User user
) {
}

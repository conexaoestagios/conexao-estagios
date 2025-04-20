package br.com.conexaoestagios.dto.admin;

import br.com.conexaoestagios.entities.users.User;
import jakarta.validation.constraints.NotBlank;

public record AdminRequestDTO(
        @NotBlank(message = "Defina um usuário")
        User user
) {
}

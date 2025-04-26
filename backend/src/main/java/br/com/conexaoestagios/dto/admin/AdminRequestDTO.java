package br.com.conexaoestagios.dto.admin;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import jakarta.validation.constraints.NotNull;

public record AdminRequestDTO(
        @NotNull(message = "Defina um usuário")
        UserRequestDTO userRequestDTO
) {
}

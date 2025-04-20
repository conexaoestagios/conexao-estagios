package br.com.conexaoestagios.dto.admin;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import jakarta.validation.constraints.NotBlank;

public record AdminRequestDTO(
        @NotBlank(message = "Defina um usuário")
        UserRequestDTO userRequestDTO
) {
}

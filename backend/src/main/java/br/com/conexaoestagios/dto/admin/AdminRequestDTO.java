package br.com.conexaoestagios.dto.admin;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.validation.OnCreate;
import jakarta.validation.constraints.NotNull;

public record AdminRequestDTO(
        @NotNull(message = "Defina um usuário", groups = OnCreate.class)
        UserRequestDTO userRequestDTO
) {
}

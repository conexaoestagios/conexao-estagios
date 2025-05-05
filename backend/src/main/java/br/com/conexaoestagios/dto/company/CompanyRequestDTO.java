package br.com.conexaoestagios.dto.company;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyRequestDTO(

        @NotBlank(message = "Razão social é obrigatória", groups = OnCreate.class)
        @Size(min = 2, message = "Razão social precisa ter, pelo menos, {min} caracteres.")
        String legalName,

        @NotBlank(message = "CNPJ é obrigatório", groups = OnCreate.class)
        @Pattern(regexp = "^[0-9]{14}$", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
        String cnpj,

        @NotBlank(message = "Setor é obrigatório", groups = OnCreate.class)
        String sector,

        //TODO alterar para que o usuário fique definitivo após criar
        @NotNull(message = "Defina um usuário", groups = OnCreate.class)
        UserRequestDTO userRequestDTO
) {
}

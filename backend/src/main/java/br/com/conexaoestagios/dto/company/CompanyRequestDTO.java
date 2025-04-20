package br.com.conexaoestagios.dto.company;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyRequestDTO(

        @NotBlank(message = "Razão social é obrigatória")
        @Size(min = 2, message = "Razão social precisa ter, pelo menos, {min} caracteres.")
        String legalName,

        @NotBlank(message = "CNPJ é obrigatório")
        @Pattern(regexp = "^[0-9]{14}$", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
        String cnpj,

        @NotBlank(message = "Setor é obrigatório")
        String sector,

        @NotBlank(message = "Defina um usuário")
        UserRequestDTO userRequestDTO
) {
}

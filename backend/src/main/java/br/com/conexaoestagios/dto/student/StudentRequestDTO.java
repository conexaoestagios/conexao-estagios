package br.com.conexaoestagios.dto.student;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import jakarta.validation.constraints.*;

import java.util.List;

public record StudentRequestDTO(

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "Curso é obrigatório")
        String course,

        @NotBlank(message = "instituição é obrigatória")
        String institution,

        @NotEmpty(message = "Pelo menos uma habilidade deve ser informada")
        List<String> skills,

        @NotBlank(message = "área de interesse é obrigatória")
        String areaOfInterest,

        @NotBlank(message = "Defina um usuário")
        UserRequestDTO userRequestDTO
) {
}

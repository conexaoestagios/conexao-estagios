package br.com.conexaoestagios.dto.student;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.validation.OnCreate;
import jakarta.validation.constraints.*;

import java.util.List;

public record StudentRequestDTO(

        @NotBlank(message = "CPF é obrigatório", groups = OnCreate.class)
        @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @NotBlank(message = "Curso é obrigatório", groups = OnCreate.class)
        String course,

        @NotBlank(message = "instituição é obrigatória", groups = OnCreate.class)
        String institution,

        @NotEmpty(message = "Pelo menos uma habilidade deve ser informada", groups = OnCreate.class)
        List<String> skills,

        @NotBlank(message = "Área de interesse é obrigatória", groups = OnCreate.class)
        String areaOfInterest,

        @NotNull(message = "Defina um usuário", groups = OnCreate.class)
        UserRequestDTO userRequestDTO
) {
}

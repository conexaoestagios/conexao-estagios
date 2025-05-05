package br.com.conexaoestagios.dto.vacation;

import br.com.conexaoestagios.dto.address.AddressRequestDTO;
import br.com.conexaoestagios.enums.Modality;
import br.com.conexaoestagios.validation.OnCreate;
import jakarta.validation.constraints.*;

import java.util.List;

//TODO implementar horário
public record VacationRequestDTO(
        @NotBlank(message = "Título é obrigatório", groups = OnCreate.class)
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras no título")
        @Size(min = 2, message = "Título precisa ter, pelo menos, {min} caracteres.")
        String title,

        @NotBlank(message = "Descrição é obrigatória", groups = OnCreate.class)
        @Size(min = 2, message = "Descrição precisa ter, pelo menos, {min} caracteres.")
        String description,

        @NotBlank(message = "Pelo menos um curso alvo deve ser informado", groups = OnCreate.class)
        List<String> targetCourses,

        @NotBlank(message = "Área de atuação é obrigatória", groups = OnCreate.class)
        String areaOfActuation,

        @NotEmpty(message = "Pelo menos um requisito deve ser informado", groups = OnCreate.class)
        List<String> requirements,

        @NotNull(message = "Modalidade é obrigatória", groups = OnCreate.class)
        Modality modality,

        @NotNull(message = "Endereço incompleto", groups = OnCreate.class)
        AddressRequestDTO addressRequestDTO) {
}

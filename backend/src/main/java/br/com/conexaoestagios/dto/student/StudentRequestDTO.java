package br.com.conexaoestagios.dto.student;

import jakarta.validation.constraints.*;

import java.util.List;

public record StudentRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras no nome")
        @Size(min = 2, message = "Nome precisa ter, pelo menos, {min} caracteres.")
        String name,

        @NotBlank(message = "Nome de usuário é obrigatório, ele será usado em seu login")
        @Size(min = 2, message = "Nome de usuário precisa ter, pelo menos, {min} caracteres.")
        String username,

        @NotBlank(message = "CPF é obrigatório")
        @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @Pattern(regexp = "^https:\\/\\/www\\.linkedin\\.com\\/in\\/.*$", message = "Insira um link válido do LinkedIn que comece com https://www.linkedin.com/in/")
        String linkedin,

        @NotBlank(message = "email é obrigatório")
        @Email(message = "insira um formato de email válido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String password,

        @NotBlank(message = "Curso é obrigatório")
        String course,

        @NotBlank(message = "instituição é obrigatória")
        String institution,

        @NotEmpty(message = "Pelo menos uma habilidade deve ser informada")
        List<String> skills,

        @NotBlank(message = "área de interesse é obrigatória")
        String areaOfInterest,

        @Pattern(regexp = "^[0-9]+$", message = "O número de telefone deve conter apenas números")
        @Size(min = 10, max = 11, message = "O DDD + número de telefone deve conter entre 10 e 11 dígitos")
        String phoneNumber,

        @NotBlank(message = "cidade é obrigatória")
        String city,

        @NotBlank(message = "Estado é obrigatório")
        String state

) {
}

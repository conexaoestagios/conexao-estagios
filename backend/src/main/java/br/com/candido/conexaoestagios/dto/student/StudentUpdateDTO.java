package br.com.candido.conexaoestagios.dto.student;

import jakarta.validation.constraints.*;

import java.util.List;

public record StudentUpdateDTO(

        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras no nome")
        @Size(min = 2, message = "Nome precisa ter, pelo menos, {min} caracteres.")
        String name,
        @Size(min = 2, message = "Nome de usuário precisa ter, pelo menos, {min} caracteres.")
        String username,

        @Pattern(regexp = "^[0-9]{11}$", message = "CPF deve conter exatamente 11 dígitos numéricos")
        String cpf,

        @Pattern(regexp = "^https:\\/\\/www\\.linkedin\\.com\\/in\\/.*$", message = "Insira um link válido do LinkedIn que comece com https://www.linkedin.com/in")
        String linkedin,

        @Email(message = "insira um formato de email válido")
        String email,

        String course,

        String institution,

        List<String> skills,

        String areaOfInterest,

        @Pattern(regexp = "^[0-9]+$", message = "O telefone deve conter apenas números")
        @Size(min = 10, max = 11, message = "O DDD + número de telefone deve conter entre 10 e 11 dígitos")
        String phoneNumber,

        String city,

        String state
) {
}

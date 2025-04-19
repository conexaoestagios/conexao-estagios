package br.com.conexaoestagios.dto.admin;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AdminRequestDTO(

        @NotBlank(message = "Nome é obrigatório")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras no nome")
        @Size(min = 2, message = "Nome precisa ter, pelo menos, {min} caracteres.")
        String name,

        @NotBlank(message = "Nome de usuário é obrigatório, ele será usado em seu login")
        @Size(min = 2, message = "Nome de usuário precisa ter, pelo menos, {min} caracteres.")
        String username,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "insira um formato de email válido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String password,

        @Pattern(regexp = "^[0-9]+$", message = "O número de telefone deve conter apenas números")
        @Size(min = 10, max = 11, message = "O DDD + número de telefone deve conter entre 10 e 11 dígitos")
        String phoneNumber,

        @NotBlank(message = "cidade é obrigatória")
        String city,

        @NotBlank(message = "Estado é obrigatório")
        String state

        ) {
}

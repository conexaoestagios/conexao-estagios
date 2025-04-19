package br.com.candido.conexaoestagios.dto.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyUpdateDTO(

        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras no nome")
        @Size(min = 2, message = "Nome precisa ter, pelo menos, {min} caracteres.")
        String name,

        @Size(min = 2, message = "Nome de usuário precisa ter, pelo menos, {min} caracteres.")
        String username,

        @Size(min = 2, message = "Razão social precisa ter, pelo menos, {min} caracteres.")
        String legalName,

        @Pattern(regexp = "^[0-9]{14}$", message = "CNPJ deve conter exatamente 14 dígitos numéricos")
        String cnpj,

        @Pattern(regexp = "^https:\\/\\/www\\.linkedin\\.com\\/in\\/.*$", message = "Insira um link válido do LinkedIn que comece com https://www.linkedin.com/in")
        String linkedin,

        @Email(message = "insira um formato de email válido")
        String email,

        String sector,

        @Pattern(regexp = "^[0-9]+$", message = "O celular deve conter apenas números")
        @Size(min = 10, max = 11, message = "O DDD + número de telefone deve conter entre 10 e 11 dígitos")
        String phoneNumber,

        String city,

        String state
) {
}

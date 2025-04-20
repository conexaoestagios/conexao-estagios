package br.com.conexaoestagios.dto.user;

import br.com.conexaoestagios.dto.address.AddressRequestDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras no nome")
        @Size(min = 2, message = "Nome precisa ter, pelo menos, {min} caracteres.")
        String name,

        @Size(min = 2, message = "Nome de usuário precisa ter, pelo menos, {min} caracteres.")
        String username,

        @Pattern(regexp = "^https:\\/\\/www\\.linkedin\\.com\\/in\\/.*$", message = "Insira um link válido do LinkedIn que comece com https://www.linkedin.com/in/")
        String linkedin,

        @Email(message = "insira um formato de email válido")
        String email,

        //TODO impementar mínimo de 8 caracteres
        String password,

        @Pattern(regexp = "^[0-9]+$", message = "O número de telefone deve conter apenas números")
        @Size(min = 10, max = 11, message = "O DDD + número de telefone deve conter entre 10 e 11 dígitos")
        String phoneNumber,

        AddressRequestDTO addressRequestDTO
) {
}

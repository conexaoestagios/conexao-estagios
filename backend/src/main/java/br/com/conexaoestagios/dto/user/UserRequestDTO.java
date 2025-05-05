package br.com.conexaoestagios.dto.user;

import br.com.conexaoestagios.dto.address.AddressRequestDTO;
import br.com.conexaoestagios.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(

        @NotBlank(message = "Nome é obrigatório", groups = OnCreate.class)
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras no nome")
        @Size(min = 2, message = "Nome precisa ter, pelo menos, {min} caracteres.")
        String name,

        @NotBlank(message = "Nome de usuário é obrigatório, ele será usado em seu login", groups = OnCreate.class)
        @Size(min = 2, message = "Nome de usuário precisa ter, pelo menos, {min} caracteres.")
        String username,

        @NotBlank(message = "Email é obrigatório", groups = OnCreate.class)
        String email,

        @NotBlank(message = "Senha é obrigatória", groups = OnCreate.class)
        String password,

        @Pattern(regexp = "^[0-9]+$", message = "O número de telefone deve conter apenas números")
        @Size(min = 10, max = 11, message = "O DDD + número de telefone deve conter entre 10 e 11 dígitos")
        String phoneNumber,

        @Pattern(regexp = "^https:\\/\\/www\\.linkedin\\.com\\/in\\/.*$", message = "Insira um link válido do LinkedIn que comece com https://www.linkedin.com/in/")
        String linkedin,

        @NotNull(message = "Endereço incompleto", groups = OnCreate.class)
        AddressRequestDTO addressRequestDTO
) {
}

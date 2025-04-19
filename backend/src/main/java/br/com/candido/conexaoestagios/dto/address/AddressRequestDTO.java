package br.com.candido.conexaoestagios.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequestDTO(

        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "^[0-9]{11}$", message = "CEP deve conter exatamente 8 dígitos numéricos")
        String zipcode,

        @Pattern(regexp = "\\d+", message = "Deve conter apenas números")
        String number,

        String complement,

        @NotBlank(message = "Nome da rua é obrigatório")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras")
        @Size(min = 2, message = "Nome da rua precisa ter, pelo menos, {min} caracteres.")
        String street,

        @NotBlank(message = "Nome do bairro é obrigatório (se não houver, coloque \"centro\")")
        @Size(min = 2, message = "Nome do bairro precisa ter, pelo menos, {min} caracteres.")
        String neighborhood,

        @NotBlank(message = "Nome da cidade é obrigatório")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras")
        @Size(min = 2, message = "Nome da cidade precisa ter, pelo menos, {min} caracteres.")
        String city,

        @NotBlank(message = "Nome do Estado é obrigatório")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras")
        @Size(min = 2, message = "Nome do Estado precisa ter, pelo menos, {min} caracteres.")
        String state,

        @NotBlank(message = "Nome do país é obrigatório")
        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Utilize apenas letras")
        @Size(min = 3, message = "Nome do país precisa ter, pelo menos, {min} caracteres.")
        String country
) {
}

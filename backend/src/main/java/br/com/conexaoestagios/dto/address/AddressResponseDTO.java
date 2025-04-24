package br.com.conexaoestagios.dto.address;

public record AddressResponseDTO(
        String zipcode,
        String number,
        String complement,
        String street,
        String neighborhood,
        String city,
        String state,
        String country
) {

}

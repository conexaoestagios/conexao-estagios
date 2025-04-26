package br.com.conexaoestagios.mapper;

import br.com.conexaoestagios.dto.address.AddressRequestDTO;
import br.com.conexaoestagios.dto.address.AddressResponseDTO;
import br.com.conexaoestagios.entities.Address;

public class AddressMapper {

    public static AddressResponseDTO toDto(Address address) {
        if (address == null) return null;

        return new AddressResponseDTO(
                address.getZipCode(),
                address.getNumber(),
                address.getComplement(),
                address.getStreet(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getCountry()
        );
    }

    public static Address toEntity(AddressRequestDTO addressRequestDTO) {
        if (addressRequestDTO == null) return null;

        Address address = new Address();

        address.setZipCode(addressRequestDTO.zipcode());
        address.setNumber(addressRequestDTO.number());
        address.setComplement(addressRequestDTO.complement());
        address.setStreet(addressRequestDTO.street());
        address.setNeighborhood(addressRequestDTO.neighborhood());
        address.setCity(addressRequestDTO.city());
        address.setState(addressRequestDTO.state());
        address.setCountry(addressRequestDTO.country());

        return address;
    }
}

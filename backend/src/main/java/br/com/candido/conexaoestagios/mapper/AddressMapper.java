package br.com.candido.conexaoestagios.mapper;

import br.com.candido.conexaoestagios.dto.address.AddressRequestDTO;
import br.com.candido.conexaoestagios.dto.address.AddressResponseDTO;
import br.com.candido.conexaoestagios.entities.Address;

public class AddressMapper {

    public static AddressResponseDTO toDto(Address address) {

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

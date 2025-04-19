package br.com.conexaoestagios.mapper;

import br.com.conexaoestagios.dto.admin.AdminRequestDTO;
import br.com.conexaoestagios.dto.admin.AdminResponseDTO;
import br.com.conexaoestagios.entities.Admin;

public class AdminMapper {

    public static AdminResponseDTO toDto(Admin admin) {
        return new AdminResponseDTO(
                admin.getId(),
                admin.getName(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getPhoneNumber(),
                admin.getRole(),
                admin.getRegistrationDate()
        );
    }

    public static Admin toEntity(AdminRequestDTO adminRequestDTO) {
        Admin admin = new Admin();

        admin.setName(adminRequestDTO.name());
        admin.setUsername(adminRequestDTO.username());
        admin.setEmail(adminRequestDTO.email());
        admin.setPassword(adminRequestDTO.password());
        admin.setPhoneNumber(adminRequestDTO.phoneNumber());
        admin.setCity(adminRequestDTO.city());
        admin.setState(adminRequestDTO.state());

        return admin;
    }


}

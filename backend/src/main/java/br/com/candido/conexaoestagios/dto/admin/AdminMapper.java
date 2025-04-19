package br.com.candido.conexaoestagios.dto.admin;

import br.com.candido.conexaoestagios.entities.Admin;

public class AdminMapper {

    public static AdminResponseDTO toDto(Admin admin) {
        return new AdminResponseDTO(
                admin.getId(),
                admin.getName(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getPhoneNumber(),
                admin.getCity(),
                admin.getState(),
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

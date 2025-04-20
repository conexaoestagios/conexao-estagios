package br.com.conexaoestagios.mapper;

import br.com.conexaoestagios.dto.admin.AdminRequestDTO;
import br.com.conexaoestagios.dto.admin.AdminResponseDTO;
import br.com.conexaoestagios.entities.Admin;
import br.com.conexaoestagios.entities.users.User;

public class AdminMapper {


    public static AdminResponseDTO toDto(Admin admin) {

        return new AdminResponseDTO(
                admin.getId(),
                UserMapper.toDto(admin.getUser()));
    }

    public static Admin toEntity(AdminRequestDTO adminRequestDTO, User user) {
        Admin admin = new Admin();
        admin.setUser(user);
        return admin;
    }
}

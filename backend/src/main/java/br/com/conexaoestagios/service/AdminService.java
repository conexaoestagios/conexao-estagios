package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.admin.AdminRequestDTO;
import br.com.conexaoestagios.dto.admin.AdminResponseDTO;
import br.com.conexaoestagios.entities.Admin;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.mapper.AdminMapper;
import br.com.conexaoestagios.repository.AdminRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final UserService userService;

    public AdminResponseDTO create(@Valid AdminRequestDTO adminRequestDTO) {
        User user = userService.create(adminRequestDTO.userRequestDTO(), Role.ADMIN);
        return AdminMapper.toDto(adminRepository.save(AdminMapper.toEntity(adminRequestDTO, user)));
    }

    public List<AdminResponseDTO> findAll() {
        return adminRepository.findAll().stream().map(AdminMapper::toDto).toList();
    }

    public AdminResponseDTO findById(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NoUserException("administrador", id));
        return AdminMapper.toDto(admin);
    }

    public AdminResponseDTO update(Long id, AdminRequestDTO adminRequestDTO) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NoUserException("admin", id));
        if (admin != null) {
            User updatedUser = userService.update(admin.getUser().getId(), adminRequestDTO.userRequestDTO());
            admin.setUser(updatedUser);
        }
        return AdminMapper.toDto(adminRepository.save(admin));
    }

    //Warning: To delete an admin, please do it directly in database!

}

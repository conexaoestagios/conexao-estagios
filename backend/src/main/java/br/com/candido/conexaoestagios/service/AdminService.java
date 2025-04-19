package br.com.candido.conexaoestagios.service;

import br.com.candido.conexaoestagios.dto.admin.AdminMapper;
import br.com.candido.conexaoestagios.dto.admin.AdminRequestDTO;
import br.com.candido.conexaoestagios.dto.admin.AdminResponseDTO;
import br.com.candido.conexaoestagios.dto.admin.AdminUpdateDTO;
import br.com.candido.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.candido.conexaoestagios.entities.Admin;
import br.com.candido.conexaoestagios.entities.Student;
import br.com.candido.conexaoestagios.exceptions.NoUserException;
import br.com.candido.conexaoestagios.repository.AdminRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AdminResponseDTO create(@Valid AdminRequestDTO adminRequestDTO) {
        validateUniqueFieldsBeforeCreate(adminRequestDTO);

        Admin admin = AdminMapper.toEntity(adminRequestDTO);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        LocalDateTime localDateTime = ZonedDateTime.now(zoneId).toLocalDateTime();
        admin.setRegistrationDate(localDateTime);

        return AdminMapper.toDto(adminRepository.save(admin));
    }

    public List<AdminResponseDTO> findAll() {
        return adminRepository.findAll().stream().map(AdminMapper::toDto).toList();
    }

    public AdminResponseDTO findById(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NoUserException("administrador", id));
        return AdminMapper.toDto(admin);
    }

    public AdminResponseDTO update(Long id, AdminUpdateDTO adminUpdateDTO) {
        validateUniqueFieldsBeforeUpdate(id, adminUpdateDTO);
        validateUniqueFieldsBeforeUpdate(id, adminUpdateDTO);
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NoUserException("estudante", id));

        if (adminUpdateDTO.name() != null) admin.setName(adminUpdateDTO.name());
        if (adminUpdateDTO.username() != null) admin.setUsername(adminUpdateDTO.username());
        if (adminUpdateDTO.email() != null) admin.setEmail(adminUpdateDTO.email());
        if (adminUpdateDTO.phoneNumber() != null) admin.setPhoneNumber(adminUpdateDTO.phoneNumber());
        if (adminUpdateDTO.city() != null) admin.setCity(adminUpdateDTO.city());
        if (adminUpdateDTO.state() != null) admin.setState(adminUpdateDTO.state());

        return AdminMapper.toDto(adminRepository.save(admin));
    }

    private void validateUniqueFieldsBeforeCreate(AdminRequestDTO dto) {

        Map<String, Object> uniqueFields = new HashMap<>();

        if (dto.email() != null) uniqueFields.put("email", dto.email());
        if (dto.username() != null) uniqueFields.put("username", dto.username());
        userService.validateUniqueFields(Admin.class, null, uniqueFields);

    }

    private void validateUniqueFieldsBeforeUpdate(Long id, AdminUpdateDTO dto) {
        Map<String, Object> uniqueFields = new HashMap<>();

        if (dto.email() != null) uniqueFields.put("email", dto.email());
        if (dto.username() != null) uniqueFields.put("username", dto.username());

        userService.validateUniqueFields(Admin.class, id, uniqueFields);
    }

    //Warning: To delete an admin, please do it directly in database!

}

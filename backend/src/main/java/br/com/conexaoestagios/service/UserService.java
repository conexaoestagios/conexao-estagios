package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.dto.user.UserResponseDTO;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.exceptions.UniqueFieldViolationException;
import br.com.conexaoestagios.mapper.UserMapper;
import br.com.conexaoestagios.repository.UserRepository;
import br.com.conexaoestagios.validation.OnCreate;
import br.com.conexaoestagios.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User create(@Validated(OnCreate.class) UserRequestDTO userRequestDTO, Role role) {
        try {
            User user = UserMapper.toEntity(userRequestDTO, role);
            user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueFieldViolationException(e);
        }
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoUserException("usuário", id));
        return UserMapper.toDto(user);
    }

    public User update(Long id, @Validated(OnUpdate.class) UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoUserException("usuário", id));
        try {
            UserMapper.applyChanges(userRequestDTO, user);
            if (userRequestDTO.password() != null) {
                user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
            }
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueFieldViolationException(e);
        }
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoUserException("usuário", id);
        }
        userRepository.deleteById(id);
    }

}

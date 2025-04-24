package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.dto.user.UserResponseDTO;
import br.com.conexaoestagios.dto.user.UserUpdateDTO;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.mapper.AddressMapper;
import br.com.conexaoestagios.mapper.UserMapper;
import br.com.conexaoestagios.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User create(UserRequestDTO userRequestDTO, Role role) {
//
        User user = UserMapper.toEntity(userRequestDTO, role);
        user.setPassword(passwordEncoder.encode(userRequestDTO.password()));
        return userRepository.save(user);
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoUserException("usuário", id));
        return UserMapper.toDto(user);
    }

    //TODO implementar validador de campo já existente
    public User update(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoUserException("usuário", id));

        if (userRequestDTO.name() != null) user.setName(userRequestDTO.name());
        if (userRequestDTO.username() != null) user.setUsername(userRequestDTO.username());
        if (userRequestDTO.linkedin() != null) user.setLinkedin(userRequestDTO.linkedin());
        if (userRequestDTO.email() != null) user.setEmail(userRequestDTO.email());
        if (userRequestDTO.phoneNumber() != null) user.setPhoneNumber(userRequestDTO.phoneNumber());
        if (userRequestDTO.addressRequestDTO() != null)
            user.setAddress(AddressMapper.toEntity(userRequestDTO.addressRequestDTO()));

        return userRepository.save(user);
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoUserException("usuário", id);
        }
        userRepository.deleteById(id);
    }

}

package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.dto.user.UserResponseDTO;
import br.com.conexaoestagios.dto.user.UserUpdateDTO;
import br.com.conexaoestagios.entities.Company;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.mapper.AddressMapper;
import br.com.conexaoestagios.mapper.CompanyMapper;
import br.com.conexaoestagios.mapper.UserMapper;
import br.com.conexaoestagios.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //TODO criar métodos para validar campos únicos em create e update
    public UserResponseDTO create(@Valid UserRequestDTO userRequestDTO) {

        User user = UserMapper.toEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return UserMapper.toDto(userRepository.save(user));
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::toDto).toList();
    }

    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NoUserException("usuário", id));
        return UserMapper.toDto(user);
    }

    public UserResponseDTO update(Long id, UserUpdateDTO userUpdateDTO) {

        User user = userRepository.findById(id).orElseThrow(() -> new NoUserException("usuário", id));

        if (userUpdateDTO.name() != null) user.setName(userUpdateDTO.name());
        if (userUpdateDTO.username() != null) user.setUsername(userUpdateDTO.username());
        if (userUpdateDTO.linkedin() != null) user.setLinkedin(userUpdateDTO.linkedin());
        if (userUpdateDTO.email() != null) user.setEmail(userUpdateDTO.email());
        if (userUpdateDTO.phoneNumber() != null) user.setPhoneNumber(userUpdateDTO.phoneNumber());
        if (userUpdateDTO.addressRequestDTO() != null)
            user.setAddress(AddressMapper.toEntity(userUpdateDTO.addressRequestDTO()));

        return UserMapper.toDto(userRepository.save(user));
    }

    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NoUserException("usuário", id);
        }
        User user = userRepository.getReferenceById(id);
        userRepository.delete(user);
    }
}

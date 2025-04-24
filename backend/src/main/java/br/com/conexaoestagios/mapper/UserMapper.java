package br.com.conexaoestagios.mapper;


import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.dto.user.UserResponseDTO;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;

public class UserMapper {

    public static UserResponseDTO toDto(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getUsername(),
                user.getLinkedin(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole(),
                AddressMapper.toDto(user.getAddress()),
                user.getRegistrationDate()
        );
    }

    public static User toEntity(UserRequestDTO userRequestDTO, Role role) {

        User user = new User();

        user.setName(userRequestDTO.name());
        user.setUsername(userRequestDTO.username());
        user.setLinkedin(userRequestDTO.linkedin());
        user.setEmail(userRequestDTO.email());
        user.setPhoneNumber(userRequestDTO.phoneNumber());
        user.setAddress(AddressMapper.toEntity(userRequestDTO.addressRequestDTO()));
        user.setRole(role);

        return user;
    }

}

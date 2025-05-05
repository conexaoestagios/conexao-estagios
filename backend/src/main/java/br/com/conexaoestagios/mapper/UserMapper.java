package br.com.conexaoestagios.mapper;


import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.dto.user.UserResponseDTO;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;

public class UserMapper {

    public static UserResponseDTO toDto(User user) {
        if (user == null) return null;

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
        if (userRequestDTO == null) return null;
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

    public static void applyChanges(UserRequestDTO userRequestDTO, User user) {
        if (userRequestDTO.name() != null) user.setName(userRequestDTO.name());
        if (userRequestDTO.username() != null) user.setUsername(userRequestDTO.username());
        if (userRequestDTO.linkedin() != null) user.setLinkedin(userRequestDTO.linkedin());
        if (userRequestDTO.email() != null) user.setEmail(userRequestDTO.email());
        if (userRequestDTO.phoneNumber() != null) user.setPhoneNumber(userRequestDTO.phoneNumber());
        if (userRequestDTO.addressRequestDTO() != null)
            user.setAddress(AddressMapper.toEntity(userRequestDTO.addressRequestDTO()));

    }
}

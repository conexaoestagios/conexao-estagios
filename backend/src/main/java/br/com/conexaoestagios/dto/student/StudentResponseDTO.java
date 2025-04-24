package br.com.conexaoestagios.dto.student;

import br.com.conexaoestagios.dto.user.UserResponseDTO;

import java.util.List;

public record StudentResponseDTO(
        Long id,
        String course,
        String institution,
        List<String> skills,
        String areaOfInterest,
        UserResponseDTO userResponseDto
) {
}

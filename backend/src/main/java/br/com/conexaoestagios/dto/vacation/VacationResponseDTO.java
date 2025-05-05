package br.com.conexaoestagios.dto.vacation;

import br.com.conexaoestagios.dto.address.AddressResponseDTO;
import br.com.conexaoestagios.enums.Modality;

import java.util.List;

public record VacationResponseDTO(
        String title,
        String description,
        List<String> targetCourses,
        String areaOfActuation,
        List<String> requirements,
        Modality modality,
        AddressResponseDTO addressResponseDTO) {
}

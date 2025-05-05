package br.com.conexaoestagios.mapper;

import br.com.conexaoestagios.dto.vacation.VacationRequestDTO;
import br.com.conexaoestagios.dto.vacation.VacationResponseDTO;
import br.com.conexaoestagios.entities.Vacation;

public class VacationMapper {

    public VacationResponseDTO toDto(Vacation vacation) {
        return new VacationResponseDTO(
                vacation.getTitle(),
                vacation.getDescription(),
                vacation.getTargetCourses(),
                vacation.getAreaOfActuation(),
                vacation.getRequirements(),
                vacation.getModality(),
                AddressMapper.toDto(vacation.getAddress())

        );
    }

    public static Vacation toEntity(VacationRequestDTO vacationRequestDTO) {
        if (vacationRequestDTO == null) return null;
        Vacation vacation = new Vacation();

        vacation.setTitle(vacationRequestDTO.title());
        vacation.setDescription(vacationRequestDTO.description());
        vacation.setTargetCourses(vacationRequestDTO.targetCourses());
        vacation.setAreaOfActuation(vacationRequestDTO.areaOfActuation());
        vacation.setModality(vacationRequestDTO.modality());
        vacation.setRequirements(vacationRequestDTO.requirements());
        vacation.setModality(vacationRequestDTO.modality());
        vacation.setAddress(AddressMapper.toEntity(vacationRequestDTO.addressRequestDTO()));

        return vacation;
    }


}

package br.com.conexaoestagios.mapper;

import br.com.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.conexaoestagios.entities.Company;

public class CompanyMapper {

    public static CompanyResponseDTO toDto(Company company) {

        return new CompanyResponseDTO(
                company.getId(),
                company.getName(),
                company.getUsername(),
                company.getLegalName(),
                company.getLinkedin(),
                company.getEmail(),
                company.getSector(),
                company.getPhoneNumber(),
                company.getCity(),
                company.getState(),
                company.getRole(),
                company.getRegistrationDate()
        );
    }

    public static Company toEntity(CompanyRequestDTO companyRequestDTO) {
        Company company = new Company();

        company.setName(companyRequestDTO.name());
        company.setUsername(companyRequestDTO.username());
        company.setLegalName(companyRequestDTO.legalName());
        company.setCnpj(companyRequestDTO.cnpj());
        company.setLinkedin(companyRequestDTO.linkedin());
        company.setEmail(companyRequestDTO.email());
        company.setPassword(companyRequestDTO.password());
        company.setSector(companyRequestDTO.sector());
        company.setPhoneNumber(companyRequestDTO.phoneNumber());
        company.setCity(companyRequestDTO.city());
        company.setState(companyRequestDTO.state());

        return company;
    }


}

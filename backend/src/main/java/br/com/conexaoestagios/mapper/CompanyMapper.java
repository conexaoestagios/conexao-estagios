package br.com.conexaoestagios.mapper;

import br.com.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.conexaoestagios.dto.user.UserResponseDTO;
import br.com.conexaoestagios.entities.Company;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;

public class CompanyMapper {

    public static CompanyResponseDTO toDto(Company company) {

        return new CompanyResponseDTO(
                company.getId(),
                company.getLegalName(),
                company.getSector(),
                UserMapper.toDto(company.getUser()));
    }

    public static Company toEntity(CompanyRequestDTO companyRequestDTO, User user) {

        Company company = new Company();

        company.setUser(user);
        company.setCnpj(companyRequestDTO.cnpj());
        company.setLegalName(companyRequestDTO.legalName());
        company.setSector(companyRequestDTO.sector());

        return company;
    }


    public static void applyChanges(CompanyRequestDTO companyRequestDTO, Company company) {
        if (companyRequestDTO.cnpj() != null) company.setCnpj(companyRequestDTO.cnpj());
        if (companyRequestDTO.legalName() != null) company.setLegalName(companyRequestDTO.legalName());
        if (companyRequestDTO.sector() != null) company.setSector(companyRequestDTO.sector());
        if (companyRequestDTO.userRequestDTO() != null) company.setUser(
                UserMapper.toEntity(companyRequestDTO.userRequestDTO(), Role.EMPRESA));
    }
}

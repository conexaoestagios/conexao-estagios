package br.com.conexaoestagios.mapper;

import br.com.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.conexaoestagios.dto.user.UserResponseDTO;
import br.com.conexaoestagios.entities.Company;
import br.com.conexaoestagios.entities.users.User;

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


}

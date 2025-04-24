package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.conexaoestagios.entities.Company;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.mapper.CompanyMapper;
import br.com.conexaoestagios.repository.CompanyRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;

    public CompanyResponseDTO create(@Valid CompanyRequestDTO companyRequestDTO) {
        User user = userService.create(companyRequestDTO.userRequestDTO(), Role.EMPRESA);
        // validateUniqueFieldsBeforeCreate(companyRequestDTO);

        Company company = companyRepository.save(CompanyMapper.toEntity(companyRequestDTO, user));
        user.setId(company.getId());

        return CompanyMapper.toDto(company);
    }

    public List<CompanyResponseDTO> findAll() {
        return companyRepository.findAll().stream().map(CompanyMapper::toDto).toList();
    }

    public CompanyResponseDTO findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NoUserException("empresa", id));
        return CompanyMapper.toDto(company);
    }

    //TODO criar método para validar usuário nulo
    public CompanyResponseDTO update(Long id, CompanyRequestDTO companyRequestDTO) {
        //  validateUniqueFieldsBeforeUpdate(id, companyRequestDTO);
        Company company = companyRepository.findById(id).orElseThrow(() -> new NoUserException("empresa", id));

        if (companyRequestDTO.cnpj() != null) company.setCnpj(companyRequestDTO.cnpj());
        if (companyRequestDTO.userRequestDTO() != null) userService.update(id, companyRequestDTO.userRequestDTO());
        if (companyRequestDTO.legalName() != null) company.setLegalName(companyRequestDTO.legalName());
        if (companyRequestDTO.sector() != null) company.setSector(companyRequestDTO.sector());

        return CompanyMapper.toDto(companyRepository.save(company));
    }

    public void delete(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NoUserException("estudante", id);
        }
        Company company = companyRepository.getReferenceById(id);
        companyRepository.delete(company);
    }

}

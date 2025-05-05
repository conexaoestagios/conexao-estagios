package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.conexaoestagios.entities.Company;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.exceptions.UniqueFieldViolationException;
import br.com.conexaoestagios.mapper.CompanyMapper;
import br.com.conexaoestagios.mapper.UserMapper;
import br.com.conexaoestagios.repository.CompanyRepository;
import br.com.conexaoestagios.validation.OnCreate;
import br.com.conexaoestagios.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final UserService userService;

    public CompanyResponseDTO create(@Validated(OnCreate.class) CompanyRequestDTO companyRequestDTO) {
        User user = userService.create(companyRequestDTO.userRequestDTO(), Role.EMPRESA);

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

    public CompanyResponseDTO update(Long id, @Validated(OnUpdate.class) CompanyRequestDTO companyRequestDTO) {

        Company company = companyRepository.findById(id).orElseThrow(() -> new NoUserException("empresa", id));

        try {
            if (companyRequestDTO.userRequestDTO() != null) userService.update(id, companyRequestDTO.userRequestDTO());
            CompanyMapper.applyChanges(companyRequestDTO, company);

            return CompanyMapper.toDto(companyRepository.save(company));
        } catch (
                DataIntegrityViolationException e) {
            throw new UniqueFieldViolationException(e);
        }
    }

    public void delete(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NoUserException("empresa", id);
        }
        Company company = companyRepository.getReferenceById(id);
        companyRepository.delete(company);
    }

}

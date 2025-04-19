package br.com.candido.conexaoestagios.service;

import br.com.candido.conexaoestagios.dto.company.CompanyMapper;
import br.com.candido.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.candido.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.candido.conexaoestagios.dto.company.CompanyUpdateDTO;
import br.com.candido.conexaoestagios.entities.Company;
import br.com.candido.conexaoestagios.entities.Student;
import br.com.candido.conexaoestagios.exceptions.NoUserException;
import br.com.candido.conexaoestagios.repository.CompanyRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public CompanyResponseDTO create(@Valid CompanyRequestDTO companyRequestDTO) {
        validateUniqueFieldsBeforeCreate(companyRequestDTO);

        Company company = CompanyMapper.toEntity(companyRequestDTO);
        company.setPassword(passwordEncoder.encode(company.getPassword()));

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        LocalDateTime localDateTime = ZonedDateTime.now(zoneId).toLocalDateTime();
        company.setRegistrationDate(localDateTime);

        return CompanyMapper.toDto(companyRepository.save(company));
    }

    public List<CompanyResponseDTO> findAll() {
        return companyRepository.findAll().stream().map(CompanyMapper::toDto).toList();
    }

    public CompanyResponseDTO findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NoUserException("empresa", id));
        return CompanyMapper.toDto(company);
    }

    public CompanyResponseDTO update(Long id, CompanyUpdateDTO companyUpdateDTO) {
        validateUniqueFieldsBeforeUpdate(id, companyUpdateDTO);

        Company company = companyRepository.findById(id).orElseThrow(() -> new NoUserException("empresa", id));

        if (companyUpdateDTO.name() != null) company.setName(companyUpdateDTO.name());
        if (companyUpdateDTO.username() != null) company.setUsername(companyUpdateDTO.username());
        if (companyUpdateDTO.legalName() != null) company.setLegalName(companyUpdateDTO.legalName());
        if (companyUpdateDTO.cnpj() != null) company.setCnpj(companyUpdateDTO.cnpj());
        if (companyUpdateDTO.linkedin() != null) company.setLinkedin(companyUpdateDTO.linkedin());
        if (companyUpdateDTO.email() != null) company.setEmail(companyUpdateDTO.email());
        if (companyUpdateDTO.sector() != null) company.setSector(companyUpdateDTO.sector());
        if (companyUpdateDTO.phoneNumber() != null) company.setPhoneNumber(companyUpdateDTO.phoneNumber());
        if (companyUpdateDTO.city() != null) company.setCity(companyUpdateDTO.city());
        if (companyUpdateDTO.state() != null) company.setState(companyUpdateDTO.state());

        return CompanyMapper.toDto(companyRepository.save(company));
    }

    public void delete(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new NoUserException("estudante", id);
        }
        Company company = companyRepository.getReferenceById(id);
        companyRepository.delete(company);
    }


    private void validateUniqueFieldsBeforeCreate(CompanyRequestDTO dto) {

        Map<String, Object> uniqueFields = new HashMap<>();

        if (dto.username() != null) uniqueFields.put("username", dto.username());
        if (dto.email() != null) uniqueFields.put("email", dto.email());
        if (dto.linkedin() != null) uniqueFields.put("linkedin", dto.linkedin());
        if (dto.legalName() != null) uniqueFields.put("legalName", dto.legalName());

        userService.validateUniqueFields(Company.class, null, uniqueFields);
    }

    private void validateUniqueFieldsBeforeUpdate(Long id, CompanyUpdateDTO dto) {
        Map<String, Object> uniqueFields = new HashMap<>();

        if (dto.username() != null) uniqueFields.put("username", dto.username());
        if (dto.email() != null) uniqueFields.put("email", dto.email());
        if (dto.linkedin() != null) uniqueFields.put("linkedin", dto.linkedin());
        if (dto.legalName() != null) uniqueFields.put("legalName", dto.legalName());

        userService.validateUniqueFields(Company.class, id, uniqueFields);
    }

}

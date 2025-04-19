package br.com.candido.conexaoestagios.servicetest;

import br.com.candido.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.candido.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.candido.conexaoestagios.dto.company.CompanyUpdateDTO;
import br.com.candido.conexaoestagios.entities.Company;
import br.com.candido.conexaoestagios.exceptions.ConflictException;
import br.com.candido.conexaoestagios.exceptions.NoUserException;
import br.com.candido.conexaoestagios.repository.CompanyRepository;
import br.com.candido.conexaoestagios.service.CompanyService;
import br.com.candido.conexaoestagios.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {

    @InjectMocks
    private CompanyService companyService;

    @Mock
    private Company company;
    @Mock
    private CompanyRequestDTO companyRequestDTO;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        company = new Company();
        company.setId(1L);
        company.setName("Tech Corp");
        company.setUsername("techcorp");
        company.setEmail("contact@techcorp.com");
        company.setPassword("password123");
        company.setSector("TI");
        company.setPhoneNumber("11999999999");
        company.setRegistrationDate(LocalDateTime.now());

        companyRequestDTO = new CompanyRequestDTO(
                company.getName(), company.getUsername(), company.getEmail(),
                company.getPassword(), company.getSector(),
                company.getPhoneNumber(), company.getCity(), company.getState());
    }

    @Test
    void should_Create_Company() {
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword123");

        CompanyResponseDTO createdCompany = companyService.create(companyRequestDTO);

        assertNotNull(createdCompany);
        assertEquals(company.getName(), createdCompany.name());
        assertEquals(company.getEmail(), createdCompany.email());
        assertEquals(company.getRole(), createdCompany.role());
        verify(passwordEncoder, times(1)).encode(companyRequestDTO.password());
        verify(companyRepository, times(1)).save(any(Company.class));
    }

    @Test
    void should_throw_ConflictException() {
        doThrow(new ConflictException("nome de usuário já está em uso."))
                .when(userService).verifyIfUsernameIsUsed(companyRequestDTO.username());
        doThrow(new ConflictException("email já está em uso."))
                .when(userService).verifyIfEmailIsUsed(companyRequestDTO.email());
        assertThrows(ConflictException.class, () -> companyService.create(companyRequestDTO));
    }

    @Test
    void should_FindCompanyByID() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Optional<CompanyResponseDTO> foundCompany = Optional.of(companyService.findById(1L));

        assertNotNull(foundCompany);
        assertEquals(company.getId(), foundCompany.get().id());
        assertEquals(company.getName(), foundCompany.get().name());
        assertEquals(company.getRole(), foundCompany.get().role());
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void should_throw_NoCompanyException() {
        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoUserException.class, () -> companyService.findById(1L));
    }

    @Test
    void should_ReturnAllCompanies() {
        Company anotherCompany = new Company();
        anotherCompany.setId(2L);
        anotherCompany.setName("Another Corp");
        anotherCompany.setEmail("another@example.com");
        anotherCompany.setPassword("password1234");
        anotherCompany.setPhoneNumber("11988888888");
        anotherCompany.setRegistrationDate(LocalDateTime.now());

        when(companyRepository.findAll()).thenReturn(List.of(company, anotherCompany));

        List<CompanyResponseDTO> companies = companyService.findAll();

        assertNotNull(companies);
        assertEquals(2, companies.size());
        verify(companyRepository, times(1)).findAll();
    }

    @Test
    void should_UpdateCompany() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(company)).thenReturn(company);

        company.setName("Updated Name");
        CompanyUpdateDTO companyUpdateDTO =
                new CompanyUpdateDTO(company.getName(), company.getUsername(), company.getEmail(), company.getSector(), company.getPhoneNumber(), company.getCity(), company.getState());

        CompanyResponseDTO updatedCompany = companyService.update(1L, companyUpdateDTO);

        assertNotNull(updatedCompany);
        assertEquals("Updated Name", updatedCompany.name());
        verify(companyRepository, times(1)).findById(1L);
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void should_DeleteCompany() {
        Long id = 1L;
        when(companyRepository.existsById(id)).thenReturn(true);
        when(companyRepository.getReferenceById(id)).thenReturn(company);
    }
}
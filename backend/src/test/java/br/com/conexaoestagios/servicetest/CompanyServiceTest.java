package br.com.conexaoestagios.servicetest;

import br.com.conexaoestagios.dto.address.AddressRequestDTO;
import br.com.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.entities.Company;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.exceptions.UniqueFieldViolationException;
import br.com.conexaoestagios.mapper.CompanyMapper;
import br.com.conexaoestagios.mapper.UserMapper;
import br.com.conexaoestagios.repository.CompanyRepository;
import br.com.conexaoestagios.service.CompanyService;
import br.com.conexaoestagios.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private CompanyRepository companyRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;

    private Company company;
    private User user;
    private AddressRequestDTO addressRequestDTO;
    private UserRequestDTO userRequestDTO;
    private CompanyRequestDTO companyRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        addressRequestDTO = new AddressRequestDTO("58057000", "50", "B", "Azuza", "Mangabeira",
                "João Pessoa", "Paraíba", "Brazil");

        userRequestDTO = new UserRequestDTO("Tech Corp", "techcorp", "contact@techcorp.com",
                "password123", "11999999999", "http://www.linkedin.com/in/techcorp", addressRequestDTO);

        user = UserMapper.toEntity(userRequestDTO, Role.EMPRESA);

        companyRequestDTO = new CompanyRequestDTO("Tech Corp Ltda", "12345678000190", "Tecnologia", userRequestDTO);
        company = CompanyMapper.toEntity(companyRequestDTO, user);
        company.setId(1L);
    }

    @Test
    void should_Create_Company() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword123");

        // Mockando o retorno do userService.create para não retornar null
        when(userService.create(any(UserRequestDTO.class), eq(Role.EMPRESA))).thenReturn(user);

        // Mockando o retorno do companyRepository.save
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        CompanyResponseDTO createdCompany = companyService.create(companyRequestDTO);

        assertNotNull(createdCompany);
        assertEquals(company.getUser().getName(), createdCompany.userResponseDto().name());
        assertEquals(company.getUser().getEmail(), createdCompany.userResponseDto().email());
        assertEquals(company.getUser().getRole(), createdCompany.userResponseDto().role());

        verify(companyRepository, times(1)).save(any(Company.class));
    }


    @Test
    void should_throw_UniqueFieldViolationException() {
        doThrow(new UniqueFieldViolationException(new DataIntegrityViolationException("email já em uso")))
                .when(userService).create(any(UserRequestDTO.class), eq(Role.EMPRESA));

        assertThrows(UniqueFieldViolationException.class, () -> companyService.create(companyRequestDTO));
    }

    @Test
    void should_FindCompanyByID() {
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        CompanyResponseDTO foundCompany = companyService.findById(1L);

        assertNotNull(foundCompany);
        assertEquals(company.getId(), foundCompany.id());
        assertEquals(company.getUser().getName(), foundCompany.userResponseDto().name());
        assertEquals(company.getUser().getRole(), foundCompany.userResponseDto().role());
        verify(companyRepository, times(1)).findById(1L);
    }

    @Test
    void should_throw_NoCompanyException() {
        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoUserException.class, () -> companyService.findById(1L));
    }

    @Test
    void should_ReturnAllCompanies() {
        User anotherUser = UserMapper.toEntity(userRequestDTO, Role.EMPRESA);
        Company anotherCompany = CompanyMapper.toEntity(companyRequestDTO, anotherUser);
        anotherCompany.setId(2L);

        when(companyRepository.findAll()).thenReturn(List.of(company, anotherCompany));

        List<CompanyResponseDTO> companies = companyService.findAll();

        assertNotNull(companies);
        assertEquals(2, companies.size());
        verify(companyRepository, times(1)).findAll();
    }

    // TODO: implementar lógica real de atualização para que esse teste funcione corretamente
    @Test
    void should_UpdateCompany() {

        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setName("Empresa Original");
        mockUser.setEmail("empresa@email.com");
        mockUser.setRole(Role.EMPRESA);

        company = new Company();
        company.setId(1L);
        company.setCnpj("12345678000100");
        company.setLegalName("Empresa Original");
        company.setSector("Tecnologia");
        company.setUser(mockUser);

        CompanyRequestDTO companyRequestDTO1 = new CompanyRequestDTO(
                "12345678000199", "Empresa Atualizada", "Educação", // novo setor
                new UserRequestDTO("Empresa Atualizada", "novo@email.com", "12345678900",
                        "novaSenha", null, null, null));

        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);
        when(userService.update(eq(1L), any(UserRequestDTO.class))).thenReturn(mockUser); // simula o update do usuário

        CompanyResponseDTO response = companyService.update(1L, companyRequestDTO1);

        assertNotNull(response);
        assertEquals("Empresa Atualizada", response.userResponseDto().name());
        assertEquals("Educação", response.sector());

        verify(companyRepository, times(1)).findById(1L);
        verify(companyRepository, times(1)).save(any(Company.class));
        verify(userService, times(1)).update(eq(1L), any(UserRequestDTO.class));
    }


    @Test
    void should_DeleteCompany() {
        Long id = 1L;
        when(companyRepository.existsById(id)).thenReturn(true);
        when(companyRepository.getReferenceById(id)).thenReturn(company);

        companyService.delete(id);

        verify(companyRepository, times(1)).existsById(id);
        verify(companyRepository, times(1)).getReferenceById(id);
        verify(companyRepository, times(1)).delete(company);
    }
}

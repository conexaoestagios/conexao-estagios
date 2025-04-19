package br.com.conexaoestagios.controllertest;

import br.com.conexaoestagios.controller.AuthController;
import br.com.conexaoestagios.dto.auth.LoginRequestDTO;
import br.com.conexaoestagios.dto.auth.TokenResponseDTO;
import br.com.conexaoestagios.repository.AdminRepository;
import br.com.conexaoestagios.repository.CompanyRepository;
import br.com.conexaoestagios.repository.StudentRepository;
import br.com.conexaoestagios.service.JwtService;
import br.com.conexaoestagios.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserDetailsServiceImpl userDetailsService;
    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private AdminRepository adminRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_shouldReturnToken_When_CredentialsISValid() {
        // Arrange
        String username = "usuario";
        String password = "senha";
        String encodedPassword = "senhaCodificada";
        String expectedToken = "token.jwt";

        LoginRequestDTO requestDTO = new LoginRequestDTO(username, password);
        User mockUser = new User(username, encodedPassword, Collections.emptyList());
        Authentication mockAuthentication = mock(Authentication.class);

        // Mocking
        when(companyRepository.existsByUsername(username)).thenReturn(true);

        when(userDetailsService.loadUserByUsername(username)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);
        when(jwtService.generateToken(mockAuthentication)).thenReturn(expectedToken);

        // Act
        ResponseEntity<?> response = authController.login(requestDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof TokenResponseDTO);
        assertEquals(expectedToken, ((TokenResponseDTO) response.getBody()).token());
    }


    @Test
    void login_ShouldReturn401_WhenPasswordInvalid() {


        // Arrange
        String username = "usuario";
        String password = "senha";
        String encodedPassword = "senhaErrada";

        LoginRequestDTO requestDTO = new LoginRequestDTO(username, password);
        User mockUser = new User(username, encodedPassword, Collections.emptyList());

        when(userDetailsService.loadUserByUsername(username)).thenReturn(mockUser);
        when(passwordEncoder.matches(password, encodedPassword)).thenReturn(false);

        // Act
        ResponseEntity<?> response = authController.login(requestDTO);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("dados inválidos", response.getBody());
    }

}

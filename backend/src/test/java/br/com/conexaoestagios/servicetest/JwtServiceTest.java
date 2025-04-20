package br.com.conexaoestagios.servicetest;

import br.com.conexaoestagios.entities.Company;
import br.com.conexaoestagios.entities.users.UserAuthenticated;
import br.com.conexaoestagios.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.*;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtServiceTest {

//    @Mock
//    private JwtEncoder jwtEncoder;
//
//    @Mock
//    private JwtDecoder jwtDecoder;
//
//    @InjectMocks
//    private JwtService jwtService;
//
//    @Mock
//    private Authentication authentication;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void shouldGenerateTokenSuccessfully() {
//        // Arrange
//        Company company = new Company();
//        company.setId(1L);
//
//        UserAuthenticated userAuth = new UserAuthenticated(company);
//        when(authentication.getPrincipal()).thenReturn(userAuth);
//        when(authentication.getName()).thenReturn("empresa");
//
//
//        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
//                .issuer("conexaoestagios")
//                .issuedAt(Instant.now())
//                .expiresAt(Instant.now().plusSeconds(7200))
//                .subject("empresa")
//                .claim("id", company.getId())
//                .claim("role", company.getRole().getAuthority())
//                .build();
//
//        Jwt jwt = mock(Jwt.class);
//        when(jwt.getTokenValue()).thenReturn("mocked.token.jwt");
//        when(jwtEncoder.encode(any())).thenReturn(jwt);
//
//        // Act
//        String token = jwtService.generateToken(authentication);
//
//        // Assert
//        assertNotNull(token);
//        assertEquals("mocked.token.jwt", token);
//    }
//
//    @Test
//    void shouldVerifyValidToken() {
//        // Arrange
//        String token = "valid.token";
//        Jwt mockJwt = mock(Jwt.class);
//        when(jwtDecoder.decode(token)).thenReturn(mockJwt);
//
//        // Act
//        Jwt result = jwtService.verifyToken(token);
//
//        // Assert
//        assertEquals(mockJwt, result);
//    }
//
//    @Test
//    void shouldThrowExceptionForInvalidToken() {
//        // Arrange
//        String invalidToken = "invalid.token";
//        when(jwtDecoder.decode(invalidToken)).thenThrow(new JwtException("Invalid JWT"));
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            jwtService.verifyToken(invalidToken);
//        });
//
//        assertEquals("Token inválido ou expirado", exception.getMessage());
//    }
}

package br.com.conexaoestagios.controller;

import br.com.conexaoestagios.dto.auth.LoginRequestDTO;
import br.com.conexaoestagios.dto.auth.TokenResponseDTO;
import br.com.conexaoestagios.exceptions.NoPermissionUserException;
import br.com.conexaoestagios.repository.AdminRepository;
import br.com.conexaoestagios.repository.CompanyRepository;
import br.com.conexaoestagios.repository.StudentRepository;
import br.com.conexaoestagios.service.JwtService;
import br.com.conexaoestagios.service.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@Tag(name = "Authentication", description = "Sessão para realizar login de usuário")
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final AdminRepository adminRepository;
    private final CompanyRepository companyRepository;
    private final StudentRepository studentRepository;

    @PostMapping("/auth/login")
    @Operation(summary = "Realizar login", description = "Se credenciais válidas, retorna um token para validar sessões")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        if (!isUserExisting(loginRequest.username()) || !passwordMathcer(loginRequest)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("dados inválidos");
        }

        var auth = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());
        var authentication = authenticationManager.authenticate(auth);
        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    //validators

    boolean passwordMathcer(LoginRequestDTO loginRequest) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
        return passwordEncoder.matches(loginRequest.password(), userDetails.getPassword());
    }

    boolean isUserExisting(String username) {
        return companyRepository.existsByUsername(username) ||
                studentRepository.existsByUsername(username) ||
                adminRepository.existsByUsername(username);
    }


    public static void validateAccess(@PathVariable Long id) {
        Long IdFromToken = JwtService.getUserIdFromToken();
        if (!Objects.equals(IdFromToken, id)) {
            throw new NoPermissionUserException();
        }
    }

}


package br.com.candido.conexaoestagios.service;

import br.com.candido.conexaoestagios.entities.users.User;
import br.com.candido.conexaoestagios.entities.users.UserAuthenticated;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtEncoder encoder;
    private final JwtDecoder jwtDecoder;

    public String generateToken(Authentication authentication) {
        UserAuthenticated userAuth = (UserAuthenticated) authentication.getPrincipal();
        User user = userAuth.getUser();
        Instant now = Instant.now();
        long expiry = 7200; //2h

        //TODO: verify if it is used
        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("conexaoestagios")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("id", user.getId())
                .claim("scope", List.of(user.getRole().getAuthority()))
                .build();

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    //TODO: verify with Product Owner if this method is needed
    public Jwt verifyToken(String token) {
        try {
            return jwtDecoder.decode(token);
        } catch (JwtException e) {
            throw new RuntimeException("Token inválido ou expirado", e);
        }
    }

    public static Long getUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAuthenticated) {
            UserAuthenticated userAuth = (UserAuthenticated) authentication.getPrincipal();
            return userAuth.getUser().getId();
        } else if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            return jwt.getClaim("id");
        }
        return null;
    }

}

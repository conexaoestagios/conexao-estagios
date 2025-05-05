package br.com.conexaoestagios.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {

    ADMIN("administradores do projeto"),
    ESTUDANTE("estudante"),
    EMPRESA("empresa");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

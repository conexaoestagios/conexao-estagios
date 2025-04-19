package br.com.candido.conexaoestagios.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN("administradores do projeto"),
    ESTUDANTE("estudante"),
    EMPRESA("empresa");

    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}

package br.com.candido.conexaoestagios.entities;


import br.com.candido.conexaoestagios.entities.users.User;
import br.com.candido.conexaoestagios.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "empresas")
public class Company extends User {

    @Column(nullable = false, name = "cnpj", unique = true)
    private String cnpj;

    @Column(nullable = false, name = "razao_social", unique = true)
    private String legalName;

    @Column(nullable = false, name = "setor")
    private String sector;

    public Company() {
        setRole(Role.EMPRESA);
    }
}
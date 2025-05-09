package br.com.conexaoestagios.entities;


import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "empresas")
public class Company{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "cnpj", unique = true)
    private String cnpj;

    @Column(nullable = false, name = "razao_social", unique = true)
    private String legalName;

    @Column(nullable = false, name = "setor")
    private String sector;

}
package br.com.conexaoestagios.entities;

import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "estudantes")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "cpf", unique = true)
    private String cpf;

    @Column(nullable = false, name = "curso")
    private String course;

    @Column(nullable = false, name = "instituicao")
    private String institution;

    @ElementCollection
    @CollectionTable(name = "estudante_habilidades", joinColumns = @JoinColumn(name = "estudante_id"))
    @Column(name = "habilidade")
    private List<String> skills;

    @Column(nullable = false, name = "area_interesse")
    private String areaOfInterest;
}


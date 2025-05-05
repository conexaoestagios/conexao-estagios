package br.com.conexaoestagios.entities;

import br.com.conexaoestagios.enums.Modality;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "vagas")
public class Vacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "titulo")
    private String title;

    @Column(nullable = false, name = "descricao")
    @NotBlank
    private String description;

    @Column(nullable = false, name = "curso")
    private List<String> targetCourses;

    @Column(nullable = false, name = "area")
    private String areaOfActuation;

    @ElementCollection
    @CollectionTable(name = "vaga_requisitos", joinColumns = @JoinColumn(name = "vaga_id"))
    @Column(name = "requisito")
    private List<String> requirements; //array

    @Column(nullable = false, name = "modalidade")
    private Modality modality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Address address;
}

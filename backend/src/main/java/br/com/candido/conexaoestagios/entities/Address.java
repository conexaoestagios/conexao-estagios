package br.com.candido.conexaoestagios.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "endereco")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "cep")
    private String zipCode;

    @Column(name = "numero")
    private String number;

    @Column(name = "complemento")
    private String complement;

    @Column(nullable = false, name = "rua")
    private String street;

    @Column(nullable = false, name = "bairro")
    private String neighborhood;

    @Column(nullable = false, name = "cidade")
    private String city;

    @Column(nullable = false, name = "estado")
    private String state;

    @Column(nullable = false, name = "pais")
    private String country;
}

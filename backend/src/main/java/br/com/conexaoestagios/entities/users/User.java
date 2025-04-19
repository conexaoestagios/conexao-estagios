package br.com.conexaoestagios.entities.users;

import br.com.conexaoestagios.entities.Address;
import br.com.conexaoestagios.enums.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //real name (trade name if user is a company)
    @Column(nullable = false, name = "nome")
    @NotBlank
    private String name;

    //username used in login
    @Column(nullable = false, name = "nome_de_usuario", unique = true)
    @NotBlank
    private String username;

    @Column(name = "linkedin", unique = true)
    private String linkedin;

    @Column(nullable = false, name = "email", unique = true)
    @Email
    private String email;

    @Column(nullable = false, name = "senha")
    @JsonIgnore
    private String password;

    @Column(name = "telefone")
    private String phoneNumber;

    @Column(nullable = false, name = "data_registro")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime registrationDate = LocalDateTime.now();

    @Column(nullable = false, name = "role")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    private Address address;
}

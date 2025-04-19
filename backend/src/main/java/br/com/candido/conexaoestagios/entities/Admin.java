package br.com.candido.conexaoestagios.entities;


import br.com.candido.conexaoestagios.entities.users.User;
import br.com.candido.conexaoestagios.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {
        setRole(Role.ADMIN);
    }
}

package br.com.conexaoestagios.repository;

import br.com.conexaoestagios.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    //to create:

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByLinkedin(String linkedin);

    //to update:

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByLinkedinAndIdNot(String linkedin, Long id);
}

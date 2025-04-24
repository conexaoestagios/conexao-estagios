package br.com.conexaoestagios.repository;

import br.com.conexaoestagios.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    //to create:

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByLinkedin(String linkedin);

    //to update:

    boolean existsByUsernameAndIdNot(String username, Long id);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByLinkedinAndIdNot(String linkedin, Long id);
}

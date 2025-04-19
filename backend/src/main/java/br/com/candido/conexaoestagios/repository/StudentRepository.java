package br.com.candido.conexaoestagios.repository;

import br.com.candido.conexaoestagios.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);

    boolean existsByUsername(String username);

}

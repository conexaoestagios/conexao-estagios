package br.com.candido.conexaoestagios.repository;

import br.com.candido.conexaoestagios.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByUsername(String username);

    boolean existsByUsername(String username);

}

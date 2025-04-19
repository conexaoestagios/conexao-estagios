package br.com.conexaoestagios.repository;

import br.com.conexaoestagios.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}

package br.com.conexaoestagios.repository;

import br.com.conexaoestagios.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Long> {
}

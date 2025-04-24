package br.com.conexaoestagios.repository;

import br.com.conexaoestagios.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}

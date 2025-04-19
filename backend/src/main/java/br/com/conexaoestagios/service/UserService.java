package br.com.conexaoestagios.service;

import br.com.conexaoestagios.exceptions.UniqueFieldViolationException;
import br.com.conexaoestagios.repository.AdminRepository;
import br.com.conexaoestagios.repository.CompanyRepository;
import br.com.conexaoestagios.repository.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final StudentRepository studentRepository;
    private final CompanyRepository companyRepository;
    private final AdminRepository adminRepository;
    private final EntityManagerFactory entityManagerFactory;

    private static final Map<String, String> errorMessages = Map.of(
            "email", "Email já está em uso.",
            "username", "Nome de usuário já está em uso.",
            "linkedin", "LinkedIn já está em uso.",
            "cpf", "CPF já está em uso.",
            "cnpj", "CNPF já está em uso.",
            "legalName", "Razão Social já está em uso."
    );

    public void validateUniqueFields(Class<?> entityClass, Long id, Map<String, Object> fieldsToCheck) {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<String> violations = new ArrayList<>();

        for (Map.Entry<String, Object> entry : fieldsToCheck.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();

            String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE e." + field + " = :value";
            if (id != null) {
                jpql += " AND e.id <> :id";
            }

            Query query = em.createQuery(jpql);
            query.setParameter("value", value);
            if (id != null) {
                query.setParameter("id", id);
            }

            Long count = (Long) query.getSingleResult();
            if (count > 0) {
                String message = errorMessages.getOrDefault(field, field + " já está em uso.");
                violations.add(message);
            }
        }

        em.close();

        if (!violations.isEmpty()) {
            throw new UniqueFieldViolationException(violations);
        }
    }

    //TODO implements method to verify if is a admin
    public boolean isUsernameFromThisID(Long id, String username) {
        return studentRepository.findById(id).map(u -> u.getUsername().equals(username)).orElse(false)
                || companyRepository.findById(id).map(u -> u.getUsername().equals(username)).orElse(false)
                || adminRepository.findById(id).map(u -> u.getUsername().equals(username)).orElse(false);
    }

    public boolean isEmailFromThisID(Long id, String linkedin) {
        return studentRepository.findById(id).map(u -> u.getEmail().equals(linkedin)).orElse(false)
                || companyRepository.findById(id).map(u -> u.getEmail().equals(linkedin)).orElse(false)
                || adminRepository.findById(id).map(u -> u.getEmail().equals(linkedin)).orElse(false);
    }
}

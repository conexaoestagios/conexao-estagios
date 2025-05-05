package br.com.conexaoestagios.exceptions;

import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UniqueFieldViolationException extends RuntimeException {

    private final List<String> messages;

    public UniqueFieldViolationException(DataIntegrityViolationException ex) {
        super(ex);
        this.messages = extractMessages(ex);
    }

    private List<String> extractMessages(DataIntegrityViolationException ex) {
        List<String> erros = new ArrayList<>();
        String cause = ex.getRootCause() != null ? ex.getRootCause().getMessage() : "";

        String[] fields = {
                "cnpj", "nome_de_usuario", "email",
                 "razao_social", "cpf"};
        for (String field : fields) {
            if (cause.contains(field)) {
                erros.add(field + " já está em uso.");
            }
        }
        return erros;
    }
}

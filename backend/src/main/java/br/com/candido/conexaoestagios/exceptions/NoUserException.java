package br.com.candido.conexaoestagios.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class NoUserException extends EntityNotFoundException {

    public NoUserException(String userType, Long id) {
        super(userType.toLowerCase() + " com o id (" + id + ") não existe no banco de dados");
    }
}

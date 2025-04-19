package br.com.candido.conexaoestagios.exceptions;

public class NoPermissionUserException extends RuntimeException{
    public NoPermissionUserException() {
        super("Você não tem permissão para acessar dados de outro usuário");
    }
}

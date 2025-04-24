package br.com.conexaoestagios.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class UniqueFieldViolationException extends RuntimeException {
    private final List<String> errors;

}

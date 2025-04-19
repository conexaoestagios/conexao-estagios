package br.com.conexaoestagios.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: create the privates routes
@RestController
@RequestMapping("api/v1/private")
@Tag(name = "Private", description = "Teste de acesso a rotas privadas")
public class PrivateController {

    //it's just a test to route
    @GetMapping
    @Operation(summary = "Validar Rota Privada", description = "Se autenticado, retorna uma mensagem de \"hello\"")
    public String getMessage() {
        return "Hello from private API controller";
    }

}

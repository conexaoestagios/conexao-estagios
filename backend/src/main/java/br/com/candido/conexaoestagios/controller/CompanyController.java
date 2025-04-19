package br.com.candido.conexaoestagios.controller;

import br.com.candido.conexaoestagios.dto.company.CompanyRequestDTO;
import br.com.candido.conexaoestagios.dto.company.CompanyResponseDTO;
import br.com.candido.conexaoestagios.dto.company.CompanyUpdateDTO;
import br.com.candido.conexaoestagios.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/company")
@Tag(name = "Empresas", description = "Gerenciamento de empresas")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @Operation(summary = "Criar Empresa", description = "Cria e salva uma empresa no banco de dados.")
    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyRequestDTO companyRequestDTO) {
        CompanyResponseDTO createdCompany = companyService.create(companyRequestDTO);
        URI location = URI.create("/" + createdCompany.id());
        return ResponseEntity.created(location).body(createdCompany);
    }

    @GetMapping("/all")
    @Operation(summary = "Buscar Empresas", description = "Retorna todas as empresas do banco ou, se não houver nenhuma, retorna uma lista vazia.")
    public ResponseEntity<List<CompanyResponseDTO>> findAllCompanies() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Empresa", description = "Retorna uma empresa pelo ID fornecido.")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AuthController.validateAccess(id);
        return ResponseEntity.ok(companyService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Empresa", description = "Altera as informações de uma empresa caso exista no banco de dados.")
    public ResponseEntity<?> updateCompany(@Valid @RequestBody CompanyUpdateDTO companyUpdateDTO, @PathVariable long id) {
        AuthController.validateAccess(id);
        CompanyResponseDTO updatedCompany = companyService.update(id, companyUpdateDTO);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar Empresa", description = "Apaga empresa ou, se não existir no banco, retorna uma mensagem de Empresa Inexistente.")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
        AuthController.validateAccess(id);
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

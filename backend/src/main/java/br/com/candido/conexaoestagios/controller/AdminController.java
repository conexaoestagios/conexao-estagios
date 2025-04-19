package br.com.candido.conexaoestagios.controller;

import br.com.candido.conexaoestagios.dto.admin.AdminRequestDTO;
import br.com.candido.conexaoestagios.dto.admin.AdminResponseDTO;
import br.com.candido.conexaoestagios.dto.admin.AdminUpdateDTO;
import br.com.candido.conexaoestagios.service.AdminService;
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
@RequestMapping("api/v1/admin")
@Tag(name = "Administradores", description = "Gerenciamento de administradores")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    @Operation(summary = "Criar Administrador", description = "Cria e salva um administrador no banco de dados.")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody AdminRequestDTO adminRequestDTO) {
        AdminResponseDTO createdAdmin = adminService.create(adminRequestDTO);
        URI location = URI.create("/" + createdAdmin.id());
        return ResponseEntity.created(location).body(createdAdmin);
    }

    @GetMapping("/all")
    @Operation(summary = "Buscar Administradores", description = "Retorna todos os administradores do banco ou, se não houver nenhum, retorna uma lista vazia.")
    public ResponseEntity<List<AdminResponseDTO>> findAllAdmins() {
        List<AdminResponseDTO> admins = adminService.findAll();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Administradore", description = "Retorna um administrador pelo ID fornecido.")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AuthController.validateAccess(id);
        return ResponseEntity.ok(adminService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Administrador", description = "Altera as informações de um administrador caso exista no banco de dados.")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody AdminUpdateDTO adminUpdateDTO, @PathVariable long id) {
        AuthController.validateAccess(id);
        AdminResponseDTO updatedAdmin = adminService.update(id, adminUpdateDTO);
        return ResponseEntity.ok(updatedAdmin);
    }

    //Warning: To delete an admin, please do it directly in database!
}

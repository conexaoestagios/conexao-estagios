package br.com.conexaoestagios.controller;

import br.com.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.conexaoestagios.dto.student.StudentResponseDTO;
import br.com.conexaoestagios.dto.student.StudentUpdateDTO;
import br.com.conexaoestagios.service.StudentService;
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
@RequestMapping("api/v1/student")
@Tag(name = "Estudantes", description = "Gerenciamento de estudantes")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @Operation(summary = "Criar Estudante", description = "Cria e salva um estudante no banco de dados.")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO createdStudent = studentService.create(studentRequestDTO);
        URI location = URI.create("/" + createdStudent.id());
        return ResponseEntity.created(location).body(createdStudent);
    }

    @GetMapping("/all")
    @Operation(summary = "Buscar Estudantes", description = "Retorna todos os estudantes do banco ou, se não houver nenhum, retorna uma lista vazia.")
    public ResponseEntity<List<StudentResponseDTO>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Estudante", description = "Retorna um estudante pelo ID fornecido.")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        AuthController.validateAccess(id);
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Estudante", description = "Altera as informações de um estudante caso exista no banco de dados.")
    public ResponseEntity<?> updateStudant(@Valid @RequestBody StudentUpdateDTO studentUpdateDTO, @PathVariable long id) {
        AuthController.validateAccess(id);
        StudentResponseDTO updatedStudent = studentService.update(id, studentUpdateDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Apagar Estudante", description = "Apaga o estudante ou, se não existir no banco, retorna uma mensagem de Estudante Inexistente.")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        AuthController.validateAccess(id);
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

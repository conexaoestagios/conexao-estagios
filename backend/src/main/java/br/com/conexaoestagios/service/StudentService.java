package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.conexaoestagios.dto.student.StudentResponseDTO;
import br.com.conexaoestagios.entities.Student;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.exceptions.UniqueFieldViolationException;
import br.com.conexaoestagios.mapper.CompanyMapper;
import br.com.conexaoestagios.mapper.StudentMapper;
import br.com.conexaoestagios.repository.StudentRepository;
import br.com.conexaoestagios.validation.OnCreate;
import br.com.conexaoestagios.validation.OnUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public StudentResponseDTO create(@Validated(OnCreate.class) StudentRequestDTO studentRequestDTO) {
        User user = userService.create(studentRequestDTO.userRequestDTO(), Role.ESTUDANTE);

        Student student = studentRepository.save(StudentMapper.toEntity(studentRequestDTO, user));
        user.setId(student.getId());

        return StudentMapper.toDto(student);
    }

    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll().stream().map(StudentMapper::toDto).toList();
    }

    public StudentResponseDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoUserException("estudante", id));
        return StudentMapper.toDto(student);
    }

    public StudentResponseDTO update(Long id, @Validated(OnUpdate.class) StudentRequestDTO studentRequestDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoUserException("estudante", id));

        try {
            if (studentRequestDTO.userRequestDTO() != null) userService.update(id, studentRequestDTO.userRequestDTO());
            StudentMapper.applyChanges(studentRequestDTO, student);

            return StudentMapper.toDto(studentRepository.save(student));
        } catch (
                DataIntegrityViolationException e) {
            throw new UniqueFieldViolationException(e);
        }
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoUserException("estudante", id);
        }
        Student student = studentRepository.getReferenceById(id);
        studentRepository.delete(student);
    }
}
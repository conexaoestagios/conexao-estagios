package br.com.conexaoestagios.service;

import br.com.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.conexaoestagios.dto.student.StudentResponseDTO;
import br.com.conexaoestagios.entities.Student;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.mapper.StudentMapper;
import br.com.conexaoestagios.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public StudentResponseDTO create(@Valid StudentRequestDTO studentRequestDTO) {
        User user = userService.create(studentRequestDTO.userRequestDTO(), Role.ESTUDANTE);
        //   validateUniqueFieldsBeforeCreate(studentRequestDTO);

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

    public StudentResponseDTO update(Long id, StudentRequestDTO studentRequestDTO) {
        // validateUniqueFieldsBeforeUpdate(id, studentRequestDTO);
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoUserException("estudante", id));

        if (studentRequestDTO.userRequestDTO() != null) userService.update(id, studentRequestDTO.userRequestDTO());

        if (studentRequestDTO.cpf() != null) student.setCpf(studentRequestDTO.cpf());
        if (studentRequestDTO.course() != null) student.setCourse(studentRequestDTO.course());
        if (studentRequestDTO.institution() != null) student.setInstitution(studentRequestDTO.institution());
        if (studentRequestDTO.skills() != null) student.setSkills(studentRequestDTO.skills());
        if (studentRequestDTO.areaOfInterest() != null) student.setAreaOfInterest(studentRequestDTO.areaOfInterest());

        return StudentMapper.toDto(studentRepository.save(student));
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoUserException("estudante", id);
        }
        Student student = studentRepository.getReferenceById(id);
        studentRepository.delete(student);
    }
}
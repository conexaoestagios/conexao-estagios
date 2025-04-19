package br.com.candido.conexaoestagios.service;

import br.com.candido.conexaoestagios.dto.student.StudentMapper;
import br.com.candido.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.candido.conexaoestagios.dto.student.StudentResponseDTO;
import br.com.candido.conexaoestagios.dto.student.StudentUpdateDTO;
import br.com.candido.conexaoestagios.entities.Student;
import br.com.candido.conexaoestagios.exceptions.NoUserException;
import br.com.candido.conexaoestagios.repository.StudentRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public StudentResponseDTO create(@Valid StudentRequestDTO studentRequestDTO) {
        validateUniqueFieldsBeforeCreate(studentRequestDTO);

        Student student = StudentMapper.toEntity(studentRequestDTO);
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        LocalDateTime localDateTime = ZonedDateTime.now(zoneId).toLocalDateTime();
        student.setRegistrationDate(localDateTime);

        return StudentMapper.toDto(studentRepository.save(student));
    }

    public List<StudentResponseDTO> findAll() {
        return studentRepository.findAll().stream().map(StudentMapper::toDto).toList();
    }

    public StudentResponseDTO findById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoUserException("estudante", id));
        return StudentMapper.toDto(student);
    }

    public StudentResponseDTO update(Long id, StudentUpdateDTO studentUpdateDTO) {
        validateUniqueFieldsBeforeUpdate(id, studentUpdateDTO);
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoUserException("estudante", id));

        if (studentUpdateDTO.name() != null) student.setName(studentUpdateDTO.name());
        if (studentUpdateDTO.username() != null) student.setUsername(studentUpdateDTO.username());
        if (studentUpdateDTO.cpf() != null) student.setCpf(studentUpdateDTO.cpf());
        if (studentUpdateDTO.linkedin() != null) student.setLinkedin(studentUpdateDTO.linkedin());
        if (studentUpdateDTO.email() != null) student.setEmail(studentUpdateDTO.email());
        if (studentUpdateDTO.course() != null) student.setCourse(studentUpdateDTO.course());
        if (studentUpdateDTO.institution() != null) student.setInstitution(studentUpdateDTO.institution());
        if (studentUpdateDTO.skills() != null) student.setSkills(studentUpdateDTO.skills());
        if (studentUpdateDTO.areaOfInterest() != null) student.setAreaOfInterest(studentUpdateDTO.areaOfInterest());
        if (studentUpdateDTO.phoneNumber() != null) student.setPhoneNumber(studentUpdateDTO.phoneNumber());
        if (studentUpdateDTO.city() != null) student.setCity(studentUpdateDTO.city());
        if (studentUpdateDTO.state() != null) student.setState(studentUpdateDTO.state());

        return StudentMapper.toDto(studentRepository.save(student));
    }

    public void delete(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new NoUserException("estudante", id);
        }
        Student student = studentRepository.getReferenceById(id);
        studentRepository.delete(student);
    }

    private void validateUniqueFieldsBeforeCreate(StudentRequestDTO dto) {

        Map<String, Object> uniqueFields = new HashMap<>();

        if (dto.username() != null) uniqueFields.put("username", dto.username());
        if (dto.email() != null) uniqueFields.put("email", dto.email());
        if (dto.linkedin() != null) uniqueFields.put("linkedin", dto.linkedin());
        if (dto.cpf() != null) uniqueFields.put("cpf", dto.cpf());
        userService.validateUniqueFields(Student.class, null, uniqueFields);

    }

    private void validateUniqueFieldsBeforeUpdate(Long id, StudentUpdateDTO dto) {
        Map<String, Object> uniqueFields = new HashMap<>();

        if (dto.username() != null) uniqueFields.put("username", dto.username());
        if (dto.email() != null) uniqueFields.put("email", dto.email());
        if (dto.linkedin() != null) uniqueFields.put("linkedin", dto.linkedin());
        if (dto.cpf() != null) uniqueFields.put("cpf", dto.cpf());
        userService.validateUniqueFields(Student.class, id, uniqueFields);
    }
}
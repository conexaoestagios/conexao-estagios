package br.com.conexaoestagios.servicetest;

import br.com.conexaoestagios.dto.address.AddressRequestDTO;
import br.com.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.conexaoestagios.dto.student.StudentResponseDTO;
import br.com.conexaoestagios.dto.user.UserRequestDTO;
import br.com.conexaoestagios.entities.Student;
import br.com.conexaoestagios.entities.users.User;
import br.com.conexaoestagios.enums.Role;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.exceptions.UniqueFieldViolationException;
import br.com.conexaoestagios.mapper.StudentMapper;
import br.com.conexaoestagios.mapper.UserMapper;
import br.com.conexaoestagios.repository.StudentRepository;
import br.com.conexaoestagios.service.StudentService;
import br.com.conexaoestagios.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;

    private Student student;
    private User user;
    private AddressRequestDTO addressRequestDTO;
    private UserRequestDTO userRequestDTO;
    private StudentRequestDTO studentRequestDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        addressRequestDTO = new AddressRequestDTO("58057000", "50", "B", "Azuza", "Mangabeira",
                "João Pessoa", "Paraíba", "Brazil");

        userRequestDTO = new UserRequestDTO("João", "joao123", "contact@joao.com",
                "password123", "11999999999", "http://www.linkedin.com/in/techcorp", addressRequestDTO);

        user = UserMapper.toEntity(userRequestDTO, Role.ESTUDANTE);

        studentRequestDTO = new StudentRequestDTO("12345678916", "Design", "Alura",
                null, "Frontend", userRequestDTO);
        student = StudentMapper.toEntity(studentRequestDTO, user);
        student.setId(1L);
    }

    @Test
    void should_Create_Student() {
        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword123");

        when(userService.create(any(UserRequestDTO.class), eq(Role.ESTUDANTE))).thenReturn(user);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponseDTO createdStudent = studentService.create(studentRequestDTO);

        assertNotNull(createdStudent);
        assertEquals(student.getUser().getName(), createdStudent.userResponseDto().name());
        assertEquals(student.getUser().getEmail(), createdStudent.userResponseDto().email());
        assertEquals(student.getUser().getRole(), createdStudent.userResponseDto().role());

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void should_throw_UniqueFieldViolationException() {
        doThrow(new UniqueFieldViolationException(new DataIntegrityViolationException("email já em uso")))
                .when(userService).create(any(UserRequestDTO.class), eq(Role.ESTUDANTE));

        assertThrows(UniqueFieldViolationException.class, () -> studentService.create(studentRequestDTO));
    }

    @Test
    void should_FindStudentByID() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentResponseDTO foundStudent = studentService.findById(1L);

        assertNotNull(foundStudent);
        assertEquals(student.getId(), foundStudent.id());
        assertEquals(student.getUser().getName(), foundStudent.userResponseDto().name());
        assertEquals(student.getUser().getRole(), foundStudent.userResponseDto().role());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void should_throw_NoUserException() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoUserException.class, () -> studentService.findById(1L));
    }

    @Test
    void should_ReturnAllStudents() {
        User anotherUser = UserMapper.toEntity(userRequestDTO, Role.EMPRESA);
        Student anotherStudent = StudentMapper.toEntity(studentRequestDTO, anotherUser);
        anotherStudent.setId(2L);

        when(studentRepository.findAll()).thenReturn(List.of(student, anotherStudent));

        List<StudentResponseDTO> students = studentService.findAll();

        assertNotNull(students);
        assertEquals(2, students.size());
        verify(studentRepository, times(1)).findAll();
    }

    //TODO evitar boillerplate
//    @Test
//    void should_UpdateStudent() {
//
//        User mockUser = new User();
//
//        UserRequestDTO userRequestDTO = new UserRequestDTO("Maria", "novo@email.com", "12345678900",
//                "novaSenha", null, null, null);
//
//        student = new Student();
//        student.setId(1L);
//        student.setCpf("12345678000100");
//        student.setCourse("Eletrônica");
//        student.setInstitution("Grau Técnico");
//        student.setUser(mockUser);
//
//        StudentRequestDTO studentRequestDTO1 = new StudentRequestDTO(
//                "10184537111", "Games", "Alura", null, "Games", userRequestDTO
//        );
//
//        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
//        when(studentRepository.save(any(Student.class))).thenReturn(student);
//        when(userService.update(eq(1L), any(UserRequestDTO.class))).thenReturn(student);
//
//        StudentResponseDTO response = studentService.update(1L, studentRequestDTO1);
//
//        assertNotNull(response);
//        assertEquals("Empresa Atualizada", response.userResponseDto().name());
//        assertEquals("Games", response.areaOfInterest());
//
//        verify(studentRepository, times(1)).findById(1L);
//        verify(studentRepository, times(1)).save(any(Student.class));
//        verify(userService, times(1)).update(eq(1L), any(UserRequestDTO.class));
//    }


    @Test
    void should_DeleteCompany() {
        Long id = 1L;
        when(studentRepository.existsById(id)).thenReturn(true);
        when(studentRepository.getReferenceById(id)).thenReturn(student);

        studentService.delete(id);

        verify(studentRepository, times(1)).existsById(id);
        verify(studentRepository, times(1)).getReferenceById(id);
        verify(studentRepository, times(1)).delete(student);
    }
}

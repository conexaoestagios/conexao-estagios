package br.com.conexaoestagios.servicetest;

import br.com.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.conexaoestagios.dto.student.StudentResponseDTO;
import br.com.conexaoestagios.dto.student.StudentUpdateDTO;
import br.com.conexaoestagios.entities.Student;
import br.com.conexaoestagios.exceptions.NoUserException;
import br.com.conexaoestagios.repository.StudentRepository;
import br.com.conexaoestagios.service.StudentService;
import br.com.conexaoestagios.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class StudentServiceTest {
//
//    @InjectMocks
//    private StudentService studentService;
//
//    @Mock
//    private Student student;
//    @Mock
//    private StudentRequestDTO studentRequestDTO;
//    @Mock
//    private StudentRepository studentRepository;
//    @Mock
//    private PasswordEncoder passwordEncoder;
//    @Mock
//    private UserService userService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//
//        student = new Student();
//        student.setId(1L);
//        student.setName("John Doe");
//        student.setUsername("UserJohn");
//        student.setEmail("john.doe@example.com");
//        student.setPassword("password123");
//        student.setCourse("Gestão de TI");
//        student.setInstitution("Faculdade XPTO");
//        student.setPhoneNumber("1234567890");
//        student.setRegistrationDate(LocalDateTime.now());
//
//        studentRequestDTO = new StudentRequestDTO(
//                student.getName(), student.getUsername(), student.getEmail(),
//                student.getPassword(), student.getCourse(), student.getInstitution(),
//                student.getSkills(), student.getAreaOfInterest(),
//                student.getPhoneNumber(), student.getCity(), student.getState());
//
//    }
//
//    @Test
//    void should_Create_Student() {
//        when(studentRepository.save(any(Student.class))).thenReturn(student);
//        when(passwordEncoder.encode(anyString())).thenReturn("hashedPassword123");
//
//        StudentResponseDTO createdStudent = studentService.create(studentRequestDTO);
//
//        assertNotNull(createdStudent);
//        assertEquals(student.getName(), createdStudent.name());
//        assertEquals(student.getEmail(), createdStudent.email());
//        assertEquals(student.getRole(), createdStudent.role());
//        verify(passwordEncoder, times(1)).encode(studentRequestDTO.password());
//        verify(studentRepository, times(1)).save(any(Student.class));
//    }
//
//    @Test
//    void should_throw_ConflictException() {
//        doThrow(new ConflictException("nome de usuário já está em uso."))
//                .when(userService).verifyIfUsernameIsUsed(studentRequestDTO.username());
//        doThrow(new ConflictException("email já está em uso."))
//                .when(userService).verifyIfEmailIsUsed(studentRequestDTO.email());
//        assertThrows(ConflictException.class, () -> studentService.create(studentRequestDTO));
//    }
//
//    @Test
//    void should_FindStudentByID() {
//        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
//
//        Optional<StudentResponseDTO> foundStudent = Optional.of(studentService.findById(1L));
//
//        assertNotNull(foundStudent);
//        assertEquals(student.getId(), foundStudent.get().id());
//        assertEquals(student.getName(), foundStudent.get().name());
//        assertEquals(student.getRole(), foundStudent.get().role());
//        verify(studentRepository, times(1)).findById(1L);
//    }
//
//    @Test
//    void should_throw_NoUserException() {
//        when(studentRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(NoUserException.class, () -> studentService.findById(1L));
//    }
//
//    @Test
//    void should_ReturnAllStudents() {
//        Student anotherStudent = new Student();
//        anotherStudent.setId(2L);
//        anotherStudent.setName("Jane");
//        anotherStudent.setEmail("jane.smith@example.com");
//        anotherStudent.setPassword("password1234");
//        anotherStudent.setPhoneNumber("83987654321");
//        anotherStudent.setRegistrationDate(LocalDateTime.now());
//
//        when(studentRepository.findAll()).thenReturn(List.of(student, anotherStudent));
//
//        List<StudentResponseDTO> estudantesDTO = studentService.findAll();
//
//        assertNotNull(estudantesDTO);
//        assertEquals(2, estudantesDTO.size());
//        verify(studentRepository, times(1)).findAll();
//    }
//
//    @Test
//    void should_UpdateStudent() {
//        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
//        when(studentRepository.save(student)).thenReturn(student);
//
//        student.setName("Updated Name");
//        StudentUpdateDTO studentUpdateDTO = new StudentUpdateDTO(
//                student.getName(), student.getUsername(), student.getEmail(), student.getCourse(),
//                student.getInstitution(), student.getSkills(), student.getAreaOfInterest(),
//                student.getPhoneNumber(), student.getCity(), student.getState());
//
//        StudentResponseDTO updatedEstudante = studentService.update(1L, studentUpdateDTO);
//
//        assertNotNull(updatedEstudante);
//        assertEquals("Updated Name", updatedEstudante.name());
//        verify(studentRepository, times(1)).findById(1L);
//        verify(studentRepository, times(1)).save(student);
//    }
//
//    @Test
//    void shoudl_DeleteStudent() {
//        Long Id = 1L;
//        when(studentRepository.existsById(Id)).thenReturn(true);
//        when(studentRepository.getReferenceById(Id)).thenReturn(student);
//
//        studentService.delete(Id);
//
//        verify(studentRepository).getReferenceById(Id);
//        verify(studentRepository).delete(student);
//    }
}

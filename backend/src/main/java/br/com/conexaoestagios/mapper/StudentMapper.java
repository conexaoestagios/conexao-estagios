package br.com.conexaoestagios.mapper;


import br.com.conexaoestagios.dto.student.StudentRequestDTO;
import br.com.conexaoestagios.dto.student.StudentResponseDTO;
import br.com.conexaoestagios.entities.Student;
import br.com.conexaoestagios.entities.users.User;

public class StudentMapper {

    public static StudentResponseDTO toDto(Student student) {

        return new StudentResponseDTO(
                student.getId(),
                student.getCourse(),
                student.getInstitution(),
                student.getSkills(),
                student.getAreaOfInterest(),
                UserMapper.toDto(student.getUser()));
    }

    public static Student toEntity(StudentRequestDTO studentRequestDTO, User user) {
        Student student = new Student();

        student.setUser(user);
        student.setCpf(studentRequestDTO.cpf());
        student.setCourse(studentRequestDTO.course());
        student.setInstitution(studentRequestDTO.institution());
        student.setAreaOfInterest(studentRequestDTO.areaOfInterest());
        student.setSkills(studentRequestDTO.skills());

        return student;
    }

}

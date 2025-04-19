package br.com.candido.conexaoestagios.dto.student;


import br.com.candido.conexaoestagios.entities.Student;

public class StudentMapper {

    public static StudentResponseDTO toDto(Student student) {

        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getUsername(),
                student.getLinkedin(),
                student.getEmail(),
                student.getCourse(),
                student.getInstitution(),
                student.getSkills(),
                student.getAreaOfInterest(),
                student.getPhoneNumber(),
                student.getCity(),
                student.getState(),
                student.getRole(),
                student.getRegistrationDate()
        );
    }

    public static Student toEntity(StudentRequestDTO studentRequestDTO) {
        System.out.println("dto "+studentRequestDTO.skills());
        Student student = new Student();

        student.setName(studentRequestDTO.name());
        student.setUsername(studentRequestDTO.username());
        student.setCpf(studentRequestDTO.cpf());
        student.setLinkedin(studentRequestDTO.linkedin());
        student.setEmail(studentRequestDTO.email());
        student.setPassword(studentRequestDTO.password());
        student.setCourse(studentRequestDTO.course());
        student.setInstitution(studentRequestDTO.institution());
        student.setSkills(studentRequestDTO.skills());
        student.setAreaOfInterest(studentRequestDTO.areaOfInterest());
        student.setPhoneNumber(studentRequestDTO.phoneNumber());
        student.setCity(studentRequestDTO.city());
        student.setState(studentRequestDTO.state());

        return student;
    }

}

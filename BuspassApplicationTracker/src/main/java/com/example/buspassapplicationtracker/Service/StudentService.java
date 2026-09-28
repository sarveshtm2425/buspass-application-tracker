package com.example.buspassapplicationtracker.Service;

import com.example.buspassapplicationtracker.Model.Student;
import com.example.buspassapplicationtracker.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return (Student) studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return (Student) studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Long id, Student student) {

        Student existingStudent =
                (Student) studentRepository.findById(id).orElse(null);

        if (existingStudent != null) {

            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setPhone(student.getPhone());

            return (Student) studentRepository.save(existingStudent);
        }

        return null;
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
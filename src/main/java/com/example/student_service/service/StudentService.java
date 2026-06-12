package com.example.student_service.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.student_service.entity.Student;
import com.example.student_service.repository.StudRepo;

@Service
public class StudentService {
    
    private final StudRepo studRepo;

    public StudentService(StudRepo studRepo) {
        this.studRepo = studRepo;
    }

    public Student createStudent(Student student){
        if(studRepo.existsByRegNumber(student.getRegNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student with this registration number already exists");
        }
        return studRepo.save(student);
    
    }

    public Student getStudentById(Long id){
        return studRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id: " + id));
    }

    public Student updateStudent(Long id, Student updatedStudent){
        Student existingStudent = getStudentById(id);
        existingStudent.setFname(updatedStudent.getFname());
        existingStudent.setLname(updatedStudent.getLname());
        existingStudent.setRegNumber(updatedStudent.getRegNumber());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setDepartment(updatedStudent.getDepartment());
        existingStudent.setLevel(updatedStudent.getLevel());
        return studRepo.save(existingStudent);
    }

    public void deleteStudent(Long id){
        Student existingStudent = getStudentById(id);
        studRepo.delete(existingStudent);
    }
}




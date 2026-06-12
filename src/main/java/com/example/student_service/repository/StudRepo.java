package com.example.student_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.student_service.entity.Student;

public interface StudRepo extends JpaRepository<Student, Long> {
    
    Optional<Student> findByRegNumber(String regNumber);

    boolean existsByRegNumber(String regNumber);

    List<Student> findByDepartment(String department);
}

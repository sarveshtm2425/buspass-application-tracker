package com.example.buspassapplicationtracker.Repository;

import com.example.buspassapplicationtracker.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
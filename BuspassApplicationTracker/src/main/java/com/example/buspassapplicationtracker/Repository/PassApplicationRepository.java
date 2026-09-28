package com.example.buspassapplicationtracker.Repository;

import com.example.buspassapplicationtracker.Model.PassApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassApplicationRepository extends JpaRepository<PassApplication, Long> {
}
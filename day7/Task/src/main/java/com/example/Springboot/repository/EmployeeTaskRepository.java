package com.example.Springboot.repository;

import com.example.Springboot.model.EmployeeTask;
import com.example.Springboot.model.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeTaskRepository extends JpaRepository<EmployeeTask, Integer> {
    List<EmployeeTask> findByEmployeeEmpId(int empId);
}
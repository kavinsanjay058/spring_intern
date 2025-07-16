package com.example.Springboot.service;

import com.example.Springboot.model.EmployeeTask;
import com.example.Springboot.repository.EmployeeTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeTaskService {
    @Autowired
    EmployeeTaskRepository employeeTaskRepository;
    public String createTask(int id , EmployeeTask employeeTask) {
        EmployeeTask newTask = new EmployeeTask();
        newTask.setTaskId(employeeTask.getTaskId());
        newTask.setTitle(employeeTask.getTitle());
        newTask.setDescription(employeeTask.getDescription());
        newTask.setDueDate(employeeTask.getDueDate());
        employeeTaskRepository.save(newTask);
        return "success";
    }

    public List<EmployeeTask> getAllTasks() {
        return employeeTaskRepository.findAll();
    }

    public List<EmployeeTask> getTasksByEmployeeId(int id) {
        return employeeTaskRepository.findByEmployeeEmpId(id);
    }

    public String updateTask(int id , EmployeeTask employeeTask) {
        EmployeeTask existing =employeeTaskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setTaskId(employeeTask.getTaskId());
        existing.setTitle(employeeTask.getTitle());
        existing.setDescription(employeeTask.getDescription());
        existing.setDueDate(employeeTask.getDueDate());
        // avoid changing empId or password here unless intentional
        employeeTaskRepository.save(existing);
        return "Employee updated successfully";
    }

    public String deleteTask(int id) {
        employeeTaskRepository.deleteById(id);
        return "Employee deleted successfully";
    }

}

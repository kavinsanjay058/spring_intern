package com.example.Springboot.service;

import com.example.Springboot.model.RegisterDetails;
import com.example.Springboot.model.Roles;
import com.example.Springboot.model.UserDetailsDto;
import com.example.Springboot.repository.RegisterDetailsRepository;
import com.example.Springboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    RegisterDetailsRepository regRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepo;

    public List<RegisterDetails> getAllEmployees(){
        return regRepo.findAll();
    }

    public RegisterDetails getEmployeeById(int id){
        return regRepo.findById(id).get();
    }

    public String addEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUsername(register.getUsername());

        Set<Roles> roles = new HashSet<>();
        for (String roleName : register.getRoleNames()) {
            Roles role = roleRepo.findByRoleName(roleName)
                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);

        regRepo.save(registerDetails);
        return "User registered successfully!";
    }

    public String updateEmployeeById(int id, RegisterDetails updatedData) {
        RegisterDetails existing = regRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setName(updatedData.getName());
        existing.setUsername(updatedData.getUsername());
        existing.setEmail(updatedData.getEmail());
        // avoid changing empId or password here unless intentional
        regRepo.save(existing);
        return "Employee updated successfully";
    }


    public String deleteEmployeeById(int id){
        regRepo.deleteById(id);
        return "Employee deleted successfully";
    }

    public String deleteAllEmployees(){
        regRepo.deleteAll();
        return "All employee deleted successfully";
    }

    public List<RegisterDetails> findEmployeesByRole(String roleName){
        List<RegisterDetails> employees = new ArrayList<>();

        for(RegisterDetails registerDetails : regRepo.findAll()){
            for(Roles role : registerDetails.getRoles()){
                if(role.getRoleName().equals(roleName.toUpperCase())){
                    employees.add(registerDetails);
                }
            }
        }
        return employees;
    }
}

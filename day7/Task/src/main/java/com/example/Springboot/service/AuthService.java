package com.example.Springboot.service;

import com.example.Springboot.model.RegisterDetails;
import com.example.Springboot.model.Roles;
import com.example.Springboot.model.UserDetailsDto;
import com.example.Springboot.repository.RegisterDetailsRepository;
import com.example.Springboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private RegisterDetailsRepository regRepo;

    @Autowired
    private RoleRepository roleRepo;

    public List<RegisterDetails> getRegisterDetails() {
        return regRepo.findAll();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    public String addNewUser(UserDetailsDto register) {
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

    public String authenticate(UserDetailsDto login) {
        Optional<RegisterDetails> optionalUser = regRepo.findByEmail(login.getEmail());

        if (optionalUser.isPresent()) {
            RegisterDetails user = optionalUser.get();
            if (passwordEncoder.matches(login.getPassword(), user.getPassword())) {
                return "User logged in successfully!";
            } else {
                return "User Login Failed!";
            }
        } else {
            return "User not found!";
        }
    }


}

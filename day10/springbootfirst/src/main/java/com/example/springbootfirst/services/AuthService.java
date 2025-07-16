package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailsRepository;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String adUserDetails(UserDetailsDto register) {
        RegisterDetails registerDetails=new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles=new HashSet<>();
        for(Roles roleName:registerDetails.getRoles()){
            Roles role=rolesRepository.findByRoleName(String.valueOf(roleName)).orElseThrow(()->new RuntimeException("Role not found"));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        registerDetailsRepository.save(registerDetails);
        return "User Details saved successfully";
    }

    public String authentication(RegisterDetails userDetails) {
        RegisterDetails user = registerDetailsRepository.findByEmail(userDetails.getEmail());
        if(user.getEmail() != null){
            if(passwordEncoder.matches(userDetails.getPassword(), user.getPassword())){
                return "user login sucessfull";
            }
            else{
                return "Enter the correct password";
            }
        }
        else{
            return "Enter the correct email";
        }

    }
}

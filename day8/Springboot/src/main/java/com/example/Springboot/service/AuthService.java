package com.example.Springboot.service;

import com.example.Springboot.jwt.JwtTokenProvider;
import com.example.Springboot.model.JwtResponse;
import com.example.Springboot.model.RegisterDetails;
import com.example.Springboot.model.Roles;
import com.example.Springboot.model.UserDetailsDto;
import com.example.Springboot.repository.RegisterDetailsRepository;
import com.example.Springboot.repository.RegisterRepository;
import com.example.Springboot.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private RegisterDetailsRepository regRepo;

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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


    public Optional<RegisterDetails> findByUserByUsername(String userName) {
        return regRepo.findByUsername(userName);
    }

    public JwtResponse authenticate(RegisterDetails registerDetails) {
        Authentication auth=AuthenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(registerDetails.getUsername(),registerDetails.getPassword())
        );
        String token=jwtTokenProvider.generateToken(auth);
        String username=registerDetails.getUsername();
        List<String> roles=Authentication.getAuthorities().stream()
                .map(role->role.getAuthority())
                .collect(Collectors.toList());
        String joinedRoles=String.join(",",roles);
        return new JwtResponse(token,username,joinedRoles);

    }
}

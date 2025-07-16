package com.example.springbootfirst.repository;

import com.example.springbootfirst.models.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails,Integer> {

    RegisterDetails findByEmail(String email);

    RegisterDetails findByUserName(String username);
}

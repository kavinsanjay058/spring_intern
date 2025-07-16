package com.example.Springboot.repository;

import com.example.Springboot.model.RegisterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterDetailsRepository extends JpaRepository<RegisterDetails, Integer> {
    Optional<RegisterDetails> findByEmail(String email);
    Optional<RegisterDetails> findByUsername(String userName);
}
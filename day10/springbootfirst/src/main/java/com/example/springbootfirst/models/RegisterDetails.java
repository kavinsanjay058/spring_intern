package com.example.springbootfirst.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@Table(name="user_details")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RegisterDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int empId;

  @Column(nullable = false)
  private String name;
  @Column(nullable = false,unique = true)
  private String email;
  @Column(nullable = false)
  private String password;
  @Column(name = "user_name",nullable = false,unique = true)
  private String userName;
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id1", referencedColumnName = "empId"),
          inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "roleId")
  )
  private Set<Roles> roles;

}

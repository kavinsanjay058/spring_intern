package com.example.Springboot.model;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;
import com.example.Springboot.model.Roles;

@Entity
@Table(name="user_details")
public class RegisterDetails {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //- if you give this you should not give this field in input this will be auto generated
    private int empId;

    //    nullable = false -> not null
    @Column(nullable = false)
    private String name;
    @Column(nullable = false , unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(name = "user_name" , nullable = false , unique = true)
    private String username;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "empId"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "roleId")
    )
    private Set<Roles> roles;

    public RegisterDetails(int empId, String name, String email, String password, String username, Set<Roles> roles) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.roles = roles;
    }

    public RegisterDetails() {

    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }
}
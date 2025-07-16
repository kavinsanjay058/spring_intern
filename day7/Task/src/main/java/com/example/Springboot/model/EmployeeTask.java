package com.example.Springboot.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "employee_tasks")
public class EmployeeTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;
    private String title;
    private String description;
    private String dueDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private RegisterDetails employee;

    public EmployeeTask(int taskId, String title, String description, String dueDate, RegisterDetails employee) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.employee = employee;
    }

    public EmployeeTask(){

    }
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public RegisterDetails getEmployee() {
        return employee;
    }

    public void setEmployee(RegisterDetails employee) {
        this.employee = employee;
    }
}

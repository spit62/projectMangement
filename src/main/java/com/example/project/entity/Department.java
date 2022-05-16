package com.example.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name="DEPARTMENT")
public class Department {

    @Id
    @Column(name = "DEPARTMENT_ID" , nullable = false)
    private String id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "department" ,cascade = CascadeType.ALL)
    Set<EmployeeDepartment> employeeDepartments = new HashSet<>();

}

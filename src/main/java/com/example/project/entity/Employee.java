package com.example.project.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of ="empId")
@Entity
@Table(name = "EMPLOYEE")
public class Employee {

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @Builder.Default
    Set<EmployeeDepartment> employeeDepartments = new HashSet<>();
    @ManyToMany(mappedBy = "employeeSet")
    @Builder.Default
    Set<Project> projects = new HashSet<>();

    @Id
    @Column(name = "EMPLOYEE_ID")
    @NonNull
    private String empId;

    @Column(name = "NAME")
    private String name;

}



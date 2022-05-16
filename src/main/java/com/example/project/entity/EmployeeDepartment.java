package com.example.project.entity;

import javax.persistence.*;

@Table
@Entity(name = "EMPLOYEE_DEPARTMENT")
public class EmployeeDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emp_id" , foreignKey = @ForeignKey(name = "EMPLOYEE_FK"))
    Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dept_id", foreignKey = @ForeignKey(name = "DEPARTMENT_FK"))
    Department department;
}

package com.example.project.repos;

import com.example.project.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DepartmentRepository extends CrudRepository<Employee,String> {
}

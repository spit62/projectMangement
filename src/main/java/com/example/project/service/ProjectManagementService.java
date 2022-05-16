package com.example.project.service;

import com.example.project.dto.EmployeeResponseDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.StatusDetailsResponseDto;

/**
 * projectManagementservice interface
 */
public interface ProjectManagementService {

    /**
     * method to retrieve emp for given project
     *
     * @param projectDto {@link ProjectDto}
     * @return {@link EmployeeResponseDto}
     */
    EmployeeResponseDto getEmployeesForProject(ProjectDto projectDto);

    /**
     * method to disassociate an employee from a project
     *
     * @param projectDto {@link ProjectDto}
     * @param employeeId employeeId
     * @return {@link StatusDetailsResponseDto}
     */
    StatusDetailsResponseDto removeEmployeeFromProject(ProjectDto projectDto, String employeeId);

}

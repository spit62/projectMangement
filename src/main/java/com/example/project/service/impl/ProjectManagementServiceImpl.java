package com.example.project.service.impl;

import com.example.project.dto.EmployeeDto;
import com.example.project.dto.EmployeeResponseDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.StatusDetailsResponseDto;
import com.example.project.entity.Employee;
import com.example.project.entity.Project;
import com.example.project.exceptions.ProjectManagementException;
import com.example.project.repos.EmployeeRepository;
import com.example.project.repos.ProjectRepository;
import com.example.project.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
public class ProjectManagementServiceImpl implements ProjectManagementService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public ProjectManagementServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    public EmployeeResponseDto getEmployeesForProject(ProjectDto projectDto) {
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        Project project = projectRepository.findByIdProjectIdAndDepartmentId(projectDto.getProjectId(),
                projectDto.getDepartmentId());
        if (null == project) {
            responseDto.setStatusCode(StatusDetailsResponseDto.FAILED);
            responseDto.setMessage("invalid Project Details");
        } else {
            responseDto.setEmployeeSet(project.getEmployeeSet().stream()
                    .map(employee -> EmployeeDto.builder().employeeName(employee.getName()).employeeId(employee.getEmpId()).build())
                    .collect(Collectors.toSet()));
        }
        return responseDto;
    }

    @Transactional
    public StatusDetailsResponseDto removeEmployeeFromProject(ProjectDto projectDto, String employeeId) {

        StatusDetailsResponseDto responseDto = new StatusDetailsResponseDto();
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        if (null != employee) {
            Project project = projectRepository.findByIdProjectIdAndDepartmentId(projectDto.getProjectId(),
                    projectDto.getDepartmentId());
            if (null == project) {
                responseDto.setStatusCode(StatusDetailsResponseDto.FAILED);
                responseDto.setMessage("invalid Project Details");
                return responseDto;
            } else if (!project.removeEmployee(employee)) {
                throw new ProjectManagementException("Association error !! Failed to remove employee from project");
            }
            responseDto.setStatusCode(StatusDetailsResponseDto.SUCCESS);
            responseDto.setMessage("Successfully Removed");
            return responseDto;
        }
        responseDto.setStatusCode(StatusDetailsResponseDto.FAILED);
        responseDto.setMessage("Invalid Employee Details");
        return responseDto;
    }
}

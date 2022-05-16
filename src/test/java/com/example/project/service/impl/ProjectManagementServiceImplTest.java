package com.example.project.service.impl;

import com.example.project.dto.EmployeeResponseDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.StatusDetailsResponseDto;
import com.example.project.entity.Employee;
import com.example.project.entity.Project;
import com.example.project.exceptions.ProjectManagementException;
import com.example.project.repos.EmployeeRepository;
import com.example.project.repos.ProjectRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class ProjectManagementServiceImplTest {

    @Mock
    ProjectRepository projectRepository;
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    ProjectManagementServiceImpl service;

    @Mock
    Project projectMock;

    Project project;

    ProjectDto projectDto;

    Employee employee;

    @Before
    public void init(){

        employee = Employee.builder().empId("EMP101").name("test_name").build();

        Set<Employee> employees = new HashSet<>();
        employees.add(employee);

        Project.ProjectCompositeId id = new Project.ProjectCompositeId();
        id.setProjectId("test_proj");
        id.setDepartmentId("test_dept");

        project = Project.builder().id(id).employeeSet(employees).build();

        projectDto = new ProjectDto();
        projectDto.setProjectId("test_proj");
        projectDto.setDepartmentId("test_dept");

    }

    @Test
    public void getEmployeesTestScenario1(){

        Mockito.when(projectRepository.findByIdProjectIdAndDepartmentId(Mockito.eq("test_proj"),
                Mockito.eq("test_dept"))).thenReturn(project);

        EmployeeResponseDto responseDto = service.getEmployeesForProject(projectDto);

        Assert.assertEquals(1,responseDto.getEmployeeSet().size());
        Assert.assertTrue(responseDto.getEmployeeSet().stream()
                .anyMatch(employeeDto -> employeeDto.getEmployeeId().equals("EMP101")));


    }

    @Test
    public void getEmployeesTestScenario2(){

        Mockito.when(projectRepository.findByIdProjectIdAndDepartmentId(Mockito.eq("test_proj"),
                Mockito.eq("test_dept"))).thenReturn(null);

        EmployeeResponseDto responseDto = service.getEmployeesForProject(projectDto);

        Assert.assertNull(responseDto.getEmployeeSet());
        Assert.assertNotNull(responseDto.getMessage());

    }

    @Test
    public void removeEmployeeTestScenario1(){

        Mockito.when(employeeRepository.findById(Mockito.eq("EMP101"))).thenReturn(Optional.of(employee));
        Mockito.when(projectRepository.findByIdProjectIdAndDepartmentId(Mockito.eq("test_proj"),
                Mockito.eq("test_dept"))).thenReturn(projectMock);
        Mockito.when(projectMock.removeEmployee(Mockito.any())).thenReturn(true);

        StatusDetailsResponseDto responseDto = service.removeEmployeeFromProject(projectDto,"EMP101");

        Assert.assertNotNull(responseDto.getStatusCode());
        Assert.assertEquals(Optional.of(0),Optional.of(responseDto.getStatusCode()));
        Assert.assertNotNull(responseDto.getMessage());

    }
    @Test
    public void removeEmployeeTestScenario2(){

        StatusDetailsResponseDto responseDto = service.removeEmployeeFromProject(projectDto,"EMP101");

        Assert.assertNotNull(responseDto.getStatusCode());
        Assert.assertEquals(Optional.of(1),Optional.of(responseDto.getStatusCode()));
        Assert.assertNotNull(responseDto.getMessage());

    }

    @Test(expected = ProjectManagementException.class)
    public void removeEmployeeTestScenario3(){

        Mockito.when(employeeRepository.findById(Mockito.eq("EMP101"))).thenReturn(Optional.of(employee));
        Mockito.when(projectRepository.findByIdProjectIdAndDepartmentId(Mockito.eq("test_proj"),
                Mockito.eq("test_dept"))).thenReturn(project);

        service.removeEmployeeFromProject(projectDto,"EMP101");

    }

    @Test
    public void removeEmployeeTestScenario4(){

        Mockito.when(employeeRepository.findById(Mockito.eq("EMP101"))).thenReturn(Optional.of(employee));

        Mockito.when(projectRepository.findByIdProjectIdAndDepartmentId(Mockito.eq("test_proj"),
                Mockito.eq("test_dept"))).thenReturn(null);

        StatusDetailsResponseDto responseDto = service.removeEmployeeFromProject(projectDto,"EMP101");

        Assert.assertNotNull(responseDto.getStatusCode());
        Assert.assertEquals(Optional.of(1),Optional.of(responseDto.getStatusCode()));
        Assert.assertNotNull(responseDto.getMessage());

    }
}

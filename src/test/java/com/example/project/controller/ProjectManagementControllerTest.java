package com.example.project.controller;

import com.example.project.dto.EmployeeResponseDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.StatusDetailsResponseDto;
import com.example.project.service.ProjectManagementService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ProjectManagementControllerTest {


    @Mock
    ProjectManagementService service;

    @InjectMocks
    ProjectManagementController controller;

    ProjectDto projectDto;

    EmployeeResponseDto responseDto = new EmployeeResponseDto();

    StatusDetailsResponseDto statusDetailsResponseDto = new StatusDetailsResponseDto();


    @Before
    public void init(){
        projectDto = new ProjectDto();
        projectDto.setProjectId("test_proj");
        projectDto.setDepartmentId("test_dept");
    }

    @Test
    public void testGetEmployeesScenario1(){

        Mockito.when(service.getEmployeesForProject(Mockito.eq(projectDto))).thenReturn(responseDto);

       ResponseEntity entity = controller.getEmployees(projectDto);

        Assert.assertEquals(HttpStatus.OK,entity.getStatusCode());
        Assert.assertEquals(responseDto,entity.getBody());

    }

    @Test
    public void testGetEmployeesScenario2(){

        responseDto.setMessage("error");

        Mockito.when(service.getEmployeesForProject(Mockito.eq(projectDto))).thenReturn(responseDto);

        ResponseEntity entity = controller.getEmployees(projectDto);

        Assert.assertEquals(HttpStatus.BAD_REQUEST,entity.getStatusCode());
        Assert.assertEquals(responseDto,entity.getBody());

    }

    @Test
    public void testRemoveEmployeeScenario1(){

        Mockito.when(service.removeEmployeeFromProject(Mockito.eq(projectDto),Mockito.eq("E1"))).thenReturn(statusDetailsResponseDto);

        ResponseEntity entity = controller.removeEmployee(projectDto,"E1");

        Assert.assertEquals(HttpStatus.NO_CONTENT,entity.getStatusCode());
        Assert.assertEquals(statusDetailsResponseDto,entity.getBody());

    }

    @Test
    public void testRemoveEmployeeScenario2(){

        statusDetailsResponseDto.setMessage("error");

        Mockito.when(service.removeEmployeeFromProject(Mockito.eq(projectDto),Mockito.eq("E1"))).thenReturn(statusDetailsResponseDto);

        ResponseEntity entity = controller.removeEmployee(projectDto,"E1");

        Assert.assertEquals(HttpStatus.BAD_REQUEST,entity.getStatusCode());
        Assert.assertEquals(statusDetailsResponseDto,entity.getBody());

    }
}

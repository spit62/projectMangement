package com.example.project.controller;

import com.example.project.dto.EmployeeResponseDto;
import com.example.project.dto.ProjectDto;
import com.example.project.dto.StatusDetailsResponseDto;
import com.example.project.service.ProjectManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for ProjectManagement
 */
@RequestMapping(value = "/projectwebservices/v1/projectmanagement" , consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class ProjectManagementController {

    private final ProjectManagementService service;

    @Autowired
    public ProjectManagementController(ProjectManagementService service) {
        this.service = service;
    }


    /**
     * Controller method for getEmployees of a project
     *
     * @param projectDto {@link ProjectDto}
     * @return ResponseEntity<EmployeeResponseDto>
     */
    @PostMapping(path="/getEmployeesForProject")
    public ResponseEntity<EmployeeResponseDto> getEmployees(@RequestBody @Validated ProjectDto projectDto)
    {
        EmployeeResponseDto responseDto = service.getEmployeesForProject(projectDto);
        String message = responseDto.getMessage();
        if (message !=null){
            return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
        }
         return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     *Controller method to disassociate a employee from a project
     *
     * @param projectDto {@link ProjectDto}
     * @param employeeId uniqueIdentified for employee Id
     * @return ResponseEntity<StatusDetailsResponseDto>
     */
    @PostMapping(path="/removeEmployeeFromProject/{id}", produces = "application/json")
    public ResponseEntity<StatusDetailsResponseDto> removeEmployee(@RequestBody @Validated ProjectDto projectDto,
                                                                   @PathVariable("id")String employeeId)
    {
        StatusDetailsResponseDto responseDto = service.removeEmployeeFromProject(projectDto,employeeId);
        String message = responseDto.getMessage();
        if (message !=null){
            return new ResponseEntity<>(responseDto,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseDto,HttpStatus.NO_CONTENT);
    }
}

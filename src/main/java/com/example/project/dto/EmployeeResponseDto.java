package com.example.project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class EmployeeResponseDto extends StatusDetailsResponseDto {

    private static final long serialVersionUID = -611724979737795209L;

    private Set<EmployeeDto> employeeSet;
}

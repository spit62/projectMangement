package com.example.project.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class ProjectDto {

    @NotBlank
    private String projectId;
    @NotBlank
    private String departmentId;

}

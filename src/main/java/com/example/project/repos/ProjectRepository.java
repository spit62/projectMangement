package com.example.project.repos;

import com.example.project.entity.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Project.ProjectCompositeId> {

    Project findByIdProjectIdAndDepartmentId(String projectId, String departmentId);
}

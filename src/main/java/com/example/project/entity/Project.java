package com.example.project.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PROJECT")
public class Project {

    @EmbeddedId
    @NonNull
    ProjectCompositeId id;

    @ManyToMany
    @JoinTable(
            name = "EMPLOYEE_PROJECT",
            joinColumns = {@JoinColumn(name = "PROJECT_ID", referencedColumnName = "PROJECT_ID"),
                    @JoinColumn(name = "DEPT_ID", referencedColumnName = "DEPARTMENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "EMP_ID")},
            foreignKey = @ForeignKey(name = "EMP_FK"),
            inverseForeignKey = @ForeignKey(name = "PROJECT_FK"))
    @Builder.Default
    Set<Employee> employeeSet = new HashSet<>();

    @Column(name = "PROJECT_NAME")
    String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("DEPARTMENT_ID")
    @JoinColumn(name = "DEPARTMENT_ID" , insertable = false , updatable = false , foreignKey = @ForeignKey(name =
            "DEPT_FK"))
    private Department department;

    public boolean removeEmployee(Employee employee) {
        if (this.employeeSet.contains(employee)) {
            boolean result;
            result = this.employeeSet.remove(employee);
            if (result)
                result = employee.projects.remove(this);
            return result;
        }
        return false;
    }

    @Embeddable
    @Setter
    @EqualsAndHashCode(of = {"projectId","departmentId"} )
    public static class ProjectCompositeId implements Serializable {
        @Column(name = "PROJECT_ID")
        @NonNull
        String projectId;
        @Column(name = "DEPARTMENT_ID")
        @NonNull
        String departmentId;
    }

}

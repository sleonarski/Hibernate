package ex1;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Set;

@Entity
public class Project {

    @ManyToMany
    @JoinTable(
            name="Project_Employee",
            joinColumns = {
                    @JoinColumn(name="project_id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "employee_id")}
    )
    private Set<Employee> employee;

    public Project(){}

    public Project(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer projectId;

    @Column (name = "name")
    private String name;

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

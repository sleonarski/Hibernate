package ex1;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
// ENUM - dodajemy @Enumrated (EnumType.STRING) - jak chcemy uzyc enuma
//swój tytuł, opis i jest konkretnego typu (np. blocker, high_priority, normal).
    @Table
    public class Task {

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Task(){}

    public Task(String title, String description, TaskType taskType){
        this.title = title;
        this.description = description;
        this.taskType = taskType;
    }

//    public Set<Task> tasks = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column (name = "title")
    private String title;

    @Column (name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated (EnumType.STRING)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKey(name = "type")
    private TaskType taskType;

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Enum getTaskType() {
        return taskType;
    }

    public void setType(TaskType taskType) {
        this.taskType = taskType;
    }



}


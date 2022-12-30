package ex1;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity

    @Table(name = "Employee") // name bedzie w sql
    public class Employee {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="phone_id")
    private Phone phone;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "car_id")
//    private Car car;

    @OneToMany(mappedBy = "employee")
    private Set<Task> tasks;

    @ManyToMany(mappedBy = "employee")
    private Set<Project> projects;




//private Set<Item> items;
    public Employee(){}

    public Employee(String firstName, String lastName,String birthDate, String mail){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.mail = mail;

    }


    public Phone getPhone() {
        return phone;
    }

//    public Car getCar() {
//        return car;
//    }

    public Set<Project> getProjects() {
        return projects;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_date")
    private String birthDate;
    @Column
    private String mail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Project> getProject(){
        return projects;
    }

    public void setProjects(Set<Project> projects){
        this.projects = projects;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "phone=" + phone +
//                ", car=" + car +
                ", tasks=" + tasks +
                ", projects=" + projects +
                ", id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}

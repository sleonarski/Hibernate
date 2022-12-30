package ex1;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;


public class Main {

    private static EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        //CRUD here

        createAndCommitEmployee("Lukas", "Podolski", "05-15-1985", "KebabGliwice@fcKoeln.de");
        createAndCommitEmployee("Robert", "Lewandowki", "12-12-1990", "Barca4Ever@gmial.com");
        createAndCommitEmployee("Jakub", "Blaszczykowski", "06-30-1978", "WisłaKraków@onet.pl");
        createAndCommitEmployee("Adam", "Malysz", "03-10-1973", "LecAdam@RekordSkoczni.pl");
        createAndCommitEmployee("Mariusz", "Pudzianowki", "06-06-988", "PolskGurom@gmial.com");
        createAndCommitEmployee("Tomasz", "Hajto", "08-22-1980", "TruskawkaNa@torcie.com");
        createAndCommitEmployee("Iga", "Swiatek", "01-13-1995", "Guwniara@zPaletkom.com");

        createAndCommitPhone("Iphone");
        createAndCommitPhone("Iphone");
        createAndCommitPhone("Samsung");
        createAndCommitPhone("Xiaomi");
        createAndCommitPhone("Samsung");
        createAndCommitPhone("Iphone");
        createAndCommitPhone("Sony");


        createAndCommitTask("MainTask","Do it ASAP",TaskType.BLOCKER);
        createAndCommitTask("CoffeTime","Make some Coffe",TaskType.NA_JUZ);
        createAndCommitTask("SecondTask","Work with new guy",TaskType.NA_JUTRO);

        updateBlockerTasks();

        entityManager.getTransaction().begin();

        Query q1 = entityManager.createQuery("update Employee set firstName = 'Arnold' where id=:id")
                .setParameter("id", 3);
        q1.executeUpdate();

        Query q2 = entityManager.createQuery("insert into Employee (firstName, lastName, mail, birthDate)" +
                        " select firstName, lastName, mail, birthDate from Employee where id=:id")
                .setParameter("id", 2);
        q2.executeUpdate();
//
//        Query q3 = entityManager.createQuery("insert into Employee (firstName , lastName, mail, birthDate)" +
//                        " values ('Kimi', 'Raikonen', 'OneMoreBeer@Ferrari.it', '07-22-1975')");
//        q3.executeUpdate();
////
//        Query q4 = entityManager.createQuery("insert into Employee (firstName, lastName, mail, birthDate" +
//                "select firstName, lastName, mail, birthDate from Employee where id=:id")
//                        .setParameter("id",3);
//        q4.executeUpdate();

        Query q5 = entityManager.createQuery("update Task set employee_id = 3 where id=:id")
                        .setParameter("id",1);
        q5.executeUpdate();

        Query q6 = entityManager.createQuery("update Task set employee_id = 5 where id=:id")
                .setParameter("id",2);
        q6.executeUpdate();

        Query q7 = entityManager.createQuery("update Task set employee_id = 7 where id=:id")
                .setParameter("id",3);
        q7.executeUpdate();


        entityManager.getTransaction().commit();

        updateEmail(); // czemu to nie dziala po zmianie imienia?

        connectPhoneAndEmployee(1);
        connectPhoneAndEmployee(2);
        connectPhoneAndEmployee(3);
        connectPhoneAndEmployee(4);
        connectPhoneAndEmployee(5);
        connectPhoneAndEmployee(6);
        connectPhoneAndEmployee(7);

        System.out.println(filterEmployeeByPhoneMark("Iphone"));





        HibernateUtil.shutdown();


    }

    public static List<Employee> filterEmployeeByPhoneMark(String mark) {
        return entityManager.createQuery("select firstName from Employee e where e.phone.mark = :mark")
                .setParameter("mark", "Iphone")
                .getResultList();
    }

    public static void deleteEmployeesWithNoTasksXXX() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Employee e where e.tasks is empty")
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

    public static void updateBlockerTasks() {
        List<Task> blockerTasks = entityManager.createQuery("select t from Task t where t.taskType = :taskType")
                .setParameter("taskType", TaskType.BLOCKER)
                .getResultList();

        entityManager.getTransaction().begin();
        for (Task t : blockerTasks) {
            t.setTitle("BLOCKER!!!" + t.getTitle());
            entityManager.merge(t);
        }
        entityManager.getTransaction().commit();
    }

    public static void updateEmail() {
        List<Employee> employees = entityManager.createQuery("select e from Employee e where e.mail not like lower(concat(e.firstName,'.',e.lastName,'@gmail.com'))", Employee.class)
                .getResultList();

        entityManager.getTransaction().begin();
        for (Employee e : employees) {
            e.setMail(e.getFirstName() + "." + e.getLastName() + "@gmail.com");
            entityManager.merge(e);

        }
        entityManager.getTransaction().commit();
    }

    public static void createAndCommitEmployee(String firstName, String lastName, String birthDate, String mail) {
        entityManager.getTransaction().begin();
        entityManager.persist(new Employee(firstName, lastName, birthDate, mail));
        System.out.println(firstName + " was successfully added to database");
        entityManager.getTransaction().commit();
    }

    public static void createAndCommitPhone(String brand) {
        entityManager.getTransaction().begin();
        entityManager.persist(new Phone(brand));
        System.out.println(brand + " was successfully added to database");
        entityManager.getTransaction().commit();

    }

    public static void connectPhoneAndEmployee(int id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Employee set phone_id = " + id + " where id=:id")
                .setParameter("id", id)
                .executeUpdate();
        entityManager.getTransaction().commit();

    }

        public static void createAndCommitTask(String title, String description, TaskType taskType){
        entityManager.getTransaction().begin();
        entityManager.persist(new Task(title, description, taskType));
            System.out.println("Task was successfully added to database");
        entityManager.getTransaction().commit();

        }




}
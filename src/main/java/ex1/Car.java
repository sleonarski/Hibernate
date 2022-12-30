//package ex1;
//
//
//import javax.persistence.*;
//
//
//@Entity
//
//    @Table
//    public class Car {
//
//    @OneToOne(mappedBy = "car", fetch = FetchType.EAGER)
//    private Employee employee;
//
//    public Car(){}
//
//    public Car(String mark, String color){
//        this.mark = mark;
//
//    }
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "car_id")
//    private Integer carId;
//
//    @Column (name = "mark")
//    private String mark;
//
//    @Column (name = "color")
//    private String color;
//
//    public Integer getCarId() {
//        return carId;
//    }
//
//    public void setCarId(Integer carId){
//        this.carId = carId;
//    }
//
//    public String getMark() {
//        return mark;
//    }
//
//    public void setMark(String mark) {
//        this.mark = mark;
//    }
//
//    public String getColor() {
//        return color;
//    }
//
//    public void setColor(String color) {
//        this.color = color;
//    }
//}

package ex1;


import javax.persistence.*;

@Entity

    @Table
    public class Phone {

    @OneToOne(mappedBy = "phone", fetch = FetchType.EAGER)
    private Employee empolyee;

    public Phone(){}
    public Phone(String mark){
        this.mark = mark;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private Integer phoneId;

    @Column (name = "mark")
    private String mark;

    public Integer getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Integer phoneId) {
        this.phoneId = phoneId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}

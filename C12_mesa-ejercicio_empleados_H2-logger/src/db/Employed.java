package db;

import java.time.LocalDate;

public class Employed {
    private int id;
    private String name;
    private int age;
    private String business;
    private LocalDate startToWork;

    public Employed(int id, String name, int age, String business, LocalDate startToWork) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.business = business;
        this.startToWork = startToWork;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public LocalDate getStartToWork() {
        return startToWork;
    }

    public void setStartToWork(LocalDate startToWork) {
        this.startToWork = startToWork;
    }
}

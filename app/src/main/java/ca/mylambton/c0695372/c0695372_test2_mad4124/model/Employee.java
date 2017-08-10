package ca.mylambton.c0695372.c0695372_test2_mad4124.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by rodrigocoutinho on 2017-08-10.
 */

public class Employee extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String birthDate;
    private Double salary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return getName();
    }
}

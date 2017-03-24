package org.hill.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 * Created by Hillawi on 23-03-17.
 */
@Entity
public class User {
    @TableGenerator(name = "USER_GEN", table = "ID_GEN", pkColumnName = "ID", valueColumnName = "VAL")
    @GeneratedValue(generator = "USER_GEN")
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

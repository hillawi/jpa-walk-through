package org.hill.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hillawi on 23-03-17.
 */
@Entity
@EntityListeners({UserPersistenceListener.class})
@Table(name = "USERS")
@NamedQueries({@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableGenerator(name = "USER_GEN", table = "ID_GEN", pkColumnName = "ID", valueColumnName = "VAL")
    @GeneratedValue(generator = "USER_GEN")
    @Id
    private long id;
    @Column(name = "FNAME", length = 50)
    private String firstName;
    @Column(name = "LNAME", length = 50)
    private String lastName;
    private int age;

    public User() {
    }

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

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

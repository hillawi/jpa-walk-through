package org.hill.jpa.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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
    @Column(name = "F_NAME", length = 50)
    private String firstName;
    @Column(name = "L_NAME", length = 50)
    private String lastName;
    @Column(name = "N_NAME")
    private String nickName;
    @Column(name = "B_DATE")
    private LocalDate birthDate;
    private Gender gender;

    public User() {
    }

    public User(String firstName, String lastName, String nickName, LocalDate birthDate, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.gender = gender;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

package org.hill.jpa.bean;

import org.hill.jpa.entity.User;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Hillawi on 23-03-17.
 */
@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    private User user;

    @PostConstruct
    public void init() {
        user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

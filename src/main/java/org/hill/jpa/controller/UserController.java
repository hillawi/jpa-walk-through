package org.hill.jpa.controller;

import org.hill.jpa.entity.User;
import org.hill.jpa.service.UserService;

import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */
@ManagedBean
@SessionScoped
public class UserController {
    @Inject
    UserService userService;

    public List<User> getUsers() {
        return userService.getUsers();
    }
}

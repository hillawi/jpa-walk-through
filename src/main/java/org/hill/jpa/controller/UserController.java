package org.hill.jpa.controller;

import org.hill.jpa.entity.User;
import org.hill.jpa.service.UserService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */
@Stateless
public class UserController {
    @EJB
    UserService userService;

    public List<User> getUsers() {
        return Arrays.asList(userService.getUser(0));
    }
}

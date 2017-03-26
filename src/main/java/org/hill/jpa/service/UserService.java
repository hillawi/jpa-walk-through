package org.hill.jpa.service;

import org.hill.jpa.entity.User;

import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */
public interface UserService {
    User createUser(User user);

    User getUser(long userId);

    List<User> getUsers();
}

package org.hill.jpa.service;

import org.hill.jpa.entity.PaginatedListWrapper;
import org.hill.jpa.entity.User;

import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */
public interface UserService {
    User createUser(User user);

    List<User> createUsers(List<User> users);

    User getUser(long userId);

    List<User> getAllUsers();

    PaginatedListWrapper<User> getUsers(PaginatedListWrapper<User> listWrapper);
}

package org.hill.jpa.service;

import org.apache.commons.lang3.StringUtils;
import org.hill.jpa.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Hillawi on 23-03-17.
 */

@Stateless
public class UserServiceImpl implements UserService {
    @PersistenceContext(unitName = "userService")
    private EntityManager entityManager;

    public User createUser(User user) {
        validate(user);
        entityManager.persist(user);
        return user;
    }

    private void validate(User user) {
        if (user == null || StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName())) {
            throw new IllegalArgumentException("The user's first and last names are mandatory.");
        }
    }

    public User getUser(long userId) {
        return entityManager.find(User.class, userId);
    }
}

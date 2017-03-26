package org.hill.jpa.service;

import org.apache.commons.lang3.StringUtils;
import org.hill.jpa.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */

@Stateless
public class UserServiceImpl implements UserService {
    @PersistenceContext(unitName = "inMemoryUserService")
    private EntityManager entityManager;

    public UserServiceImpl() {
    }

    public UserServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void init() {
        System.out.println("Post construction.");
        System.out.println(entityManager.toString());
    }

    public User createUser(User user) {
        validate(user);
        entityManager.persist(user);
        return user;
    }

    private void validate(User user) {
        if (user == null || StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName()) || user.getAge() <= 0) {
            throw new IllegalArgumentException("The user's first and last names are mandatory. The user age should be a positive integer.");
        }
    }

    public User getUser(long userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }
}
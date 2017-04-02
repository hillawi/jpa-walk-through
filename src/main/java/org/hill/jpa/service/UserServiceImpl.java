package org.hill.jpa.service;

import org.apache.commons.lang3.StringUtils;
import org.hill.jpa.entity.PaginatedListWrapper;
import org.hill.jpa.entity.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */

@RequestScoped
public class UserServiceImpl implements UserService {
    @PersistenceContext(unitName = "managedUserService")
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

    @Override
    public List<User> createUsers(List<User> users) {
        users.parallelStream().forEach(this::createUser);
        return users;
    }

    private void validate(User user) {
        if (user == null || StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName()) || StringUtils.isEmpty(user.getNickName())) {
            throw new IllegalArgumentException("The user's first and last names are mandatory. The user age should be a positive integer.");
        }
    }

    public User getUser(long userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createNamedQuery("User.findAll", User.class).getResultList();
    }

    @Override
    public PaginatedListWrapper<User> getUsers(PaginatedListWrapper<User> listWrapper) {
        listWrapper.setResultCount(countUsers());
        int start = (listWrapper.getCurrentPage() - 1) * listWrapper.getPageSize();
        listWrapper.setList(findUsers(start, listWrapper.getPageSize()));
        return listWrapper;
    }

    private List<User> findUsers(int startPosition, int maxResult) {
        Query query = entityManager.createNamedQuery("User.findAll", User.class);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    private Integer countUsers() {
        Query query = entityManager.createQuery("SELECT COUNT(u.id) FROM User u");
        return ((Long) query.getSingleResult()).intValue();
    }
}

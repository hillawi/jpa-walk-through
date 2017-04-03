package org.hill.jpa.service;

import org.hamcrest.CoreMatchers;
import org.hill.jpa.entity.Gender;
import org.hill.jpa.entity.User;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Hillawi on 25-03-17.
 */
public class EntityManagerTest {
    EntityManager entityManager;

    //@Before
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userServiceTest");
        entityManager = emf.createEntityManager();
    }

    //@Test
    public void testEntityManagerLookup() throws Exception {
        assertNotNull(entityManager);

        List<User> users = entityManager.createNamedQuery("User.findAll", User.class).getResultList();
        assertFalse(users.isEmpty());
        assertEquals(5, users.size());
    }

    //@Test
    public void testCreation() {
        User user = new User("John", "Doe", "JohnDoe", LocalDate.of(2017, 1, 1), Gender.MALE);
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        System.out.println(user);

        assertNotNull(user);
        assertThat(user.getId(), CoreMatchers.is(1L));
    }
}

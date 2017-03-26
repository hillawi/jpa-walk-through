package org.hill.jpa.service;

import org.hill.jpa.entity.User;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Hillawi on 25-03-17.
 */
public class EntityManagerTest {
    EntityManager entityManager;

    @Before
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("userServiceTest");
        entityManager = emf.createEntityManager();
    }

    @Test
    public void testEntityManagerLookup() throws Exception {
        assertNotNull(entityManager);

        List<User> users = entityManager.createNamedQuery("User.findAll", User.class).getResultList();
        assertFalse(users.isEmpty());
        assertEquals(5, users.size());
    }
}

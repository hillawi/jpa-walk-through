package org.hill.jpa.service;

import org.hamcrest.CoreMatchers;
import org.hill.jpa.entity.Customer;
import org.hill.jpa.entity.Gender;

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
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("customerServiceTest");
        entityManager = emf.createEntityManager();
    }

    //@Test
    public void testEntityManagerLookup() throws Exception {
        assertNotNull(entityManager);

        List<Customer> customers = entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();
        assertFalse(customers.isEmpty());
        assertEquals(5, customers.size());
    }

    //@Test
    public void testCreation() {
        Customer customer = new Customer("John", "Doe", "JohnDoe", LocalDate.of(2017, 1, 1), Gender.MALE);
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
        System.out.println(customer);

        assertNotNull(customer);
        assertThat(customer.getId(), CoreMatchers.is(1L));
    }
}

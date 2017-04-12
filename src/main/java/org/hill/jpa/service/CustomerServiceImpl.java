package org.hill.jpa.service;

import org.apache.commons.lang3.StringUtils;
import org.hill.jpa.model.PaginatedListWrapper;
import org.hill.jpa.model.entity.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */

@Stateless
public class CustomerServiceImpl implements CustomerService {
    @PersistenceContext(unitName = "managedService")
    private EntityManager entityManager;

    public CustomerServiceImpl() {
    }

    public CustomerServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Customer create(Customer customer) {
        validate(customer);
        entityManager.persist(customer);
        return customer;
    }

    private void validate(Customer customer) {
        if (customer == null
                || StringUtils.isEmpty(customer.getFirstName())
                || StringUtils.isEmpty(customer.getLastName())
                || StringUtils.isEmpty(customer.getNickName())) {
            throw new IllegalArgumentException("The customer's first name, last name and nickname are mandatory.");
        }
    }

    public Customer get(long customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public Customer update(Customer customer) {
        Customer updatedCustomer = entityManager.merge(customer);
        return updatedCustomer;
    }

    @Override
    public List<Customer> getAll() {
        return entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    @Override
    public PaginatedListWrapper<Customer> get(PaginatedListWrapper<Customer> listWrapper) {
        listWrapper.setResultCount(count(entityManager.createQuery("SELECT COUNT(c.id) FROM Customer c")));
        int start = (listWrapper.getCurrentPage() - 1) * listWrapper.getPageSize();
        listWrapper.setList(find(start, listWrapper.getPageSize(),
                entityManager.createNamedQuery("Customer.findAll", Customer.class)));
        return listWrapper;
    }
}

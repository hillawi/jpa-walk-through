package org.hill.jpa.service;

import org.apache.commons.lang3.StringUtils;
import org.hill.jpa.entity.Customer;
import org.hill.jpa.entity.PaginatedListWrapper;

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
public class CustomerServiceImpl implements CustomerService {
    @PersistenceContext(unitName = "managedCustomerService")
    private EntityManager entityManager;

    public CustomerServiceImpl() {
    }

    public CustomerServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostConstruct
    public void init() {
        System.out.println("Post construction.");
        System.out.println(entityManager.toString());
    }

    public Customer create(Customer customer) {
        validate(customer);
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public List<Customer> create(List<Customer> customers) {
        customers.parallelStream().forEach(this::create);
        return customers;
    }

    private void validate(Customer customer) {
        if (customer == null || StringUtils.isEmpty(customer.getFirstName()) || StringUtils.isEmpty(customer.getLastName()) || StringUtils.isEmpty(customer.getNickName())) {
            throw new IllegalArgumentException("The customer's first and last names are mandatory. The customer age should be a positive integer.");
        }
    }

    public Customer get(long customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public List<Customer> getAll() {
        return entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    @Override
    public PaginatedListWrapper<Customer> get(PaginatedListWrapper<Customer> listWrapper) {
        listWrapper.setResultCount(countCustomers());
        int start = (listWrapper.getCurrentPage() - 1) * listWrapper.getPageSize();
        listWrapper.setList(find(start, listWrapper.getPageSize()));
        return listWrapper;
    }

    private List<Customer> find(int startPosition, int maxResult) {
        Query query = entityManager.createNamedQuery("Customer.findAll", Customer.class);
        query.setFirstResult(startPosition);
        query.setMaxResults(maxResult);
        return query.getResultList();
    }

    private Integer countCustomers() {
        Query query = entityManager.createQuery("SELECT COUNT(c.id) FROM Customer c");
        return ((Long) query.getSingleResult()).intValue();
    }
}

package org.hill.jpa.bean;

import org.hill.jpa.entity.Customer;
import org.hill.jpa.service.CustomerService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */
@ManagedBean
@SessionScoped
public class CustomerBean implements Serializable {
    @Inject
    private CustomerService customerService;
    private Customer customer;

    @PostConstruct
    public void init() {
        customer = new Customer();
    }

    public void saveCustomer() {
        customerService.create(customer);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomers() {
        return customerService.getAll();
    }
}

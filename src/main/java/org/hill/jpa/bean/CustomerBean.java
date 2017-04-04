package org.hill.jpa.bean;

import org.hill.jpa.entity.Customer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Hillawi on 23-03-17.
 */
@ManagedBean
@SessionScoped
public class CustomerBean implements Serializable {
    private Customer customer;

    @PostConstruct
    public void init() {
        customer = new Customer();
    }

    public void saveCustomer() {
        System.out.println(customer + " saved.");
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

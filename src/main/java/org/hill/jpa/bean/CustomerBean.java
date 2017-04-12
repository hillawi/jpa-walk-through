package org.hill.jpa.bean;

import org.hill.jpa.model.entity.Customer;
import org.hill.jpa.service.CustomerService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */
@ManagedBean
@RequestScoped
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

    public void onEdit(RowEditEvent event) {
        this.customer = customerService.update((Customer) event.getObject());
        FacesMessage msg = new FacesMessage("Customer Edited", this.customer.getNickName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEditCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit cancelled", ((Customer) event.getObject()).getNickName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

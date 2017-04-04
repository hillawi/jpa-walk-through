package org.hill.jpa.service;

import org.hill.jpa.entity.Customer;
import org.hill.jpa.entity.PaginatedListWrapper;

import java.util.List;

/**
 * Created by Hillawi on 23-03-17.
 */
public interface CustomerService {
    Customer create(Customer customer);

    List<Customer> create(List<Customer> customers);

    Customer get(long customerId);

    List<Customer> getAll();

    PaginatedListWrapper<Customer> get(PaginatedListWrapper<Customer> listWrapper);
}

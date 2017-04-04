package org.hill.jpa.entity;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

/**
 * Created by Hillawi on 26-03-17.
 */
public class CustomerPersistenceListener {
    @PrePersist
    public void prePersist(Customer customer) {
        System.out.println(customer);
    }

    @PostLoad
    public void postLoad(Customer customer) {
        System.out.println(customer);
    }
}

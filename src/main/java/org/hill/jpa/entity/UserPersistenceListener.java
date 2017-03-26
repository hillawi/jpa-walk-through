package org.hill.jpa.entity;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

/**
 * Created by Hillawi on 26-03-17.
 */
public class UserPersistenceListener {
    @PrePersist
    public void prePersist(User user) {
        System.out.println(user);
    }

    @PostLoad
    public void postLoad(User user) {
        System.out.println(user);
    }
}

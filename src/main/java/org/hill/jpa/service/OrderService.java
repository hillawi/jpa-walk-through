package org.hill.jpa.service;

import org.hill.jpa.model.entity.Order;

import java.util.List;

/**
 * Created by Hillawi on 12-04-17.
 */
public interface OrderService extends CommonService<Order> {
    List<Order> getAllByCustomer();
}

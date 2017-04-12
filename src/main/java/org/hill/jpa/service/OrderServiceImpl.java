package org.hill.jpa.service;

import org.hill.jpa.model.PaginatedListWrapper;
import org.hill.jpa.model.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Hillawi on 12-04-17.
 */
public class OrderServiceImpl implements OrderService {
    @PersistenceContext(name = "managedService")
    EntityManager entityManager;

    @Override
    public Order create(Order order) {
        validate(order);
        order.setCreationTime(LocalDateTime.now());
        order.setModificationTime(LocalDateTime.now());
        entityManager.persist(order);
        return order;
    }

    private void validate(Order order) {
    }

    @Override
    public Order get(long id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    public Order update(Order order) {
        Order updatedOrder = entityManager.merge(order);
        return updatedOrder;
    }

    @Override
    public List<Order> getAll() {
        return entityManager.createNamedQuery("Order.findAll", Order.class).getResultList();
    }

    @Override
    public List<Order> getAllByCustomer() {
        return entityManager.createNamedQuery("Order.findAllByCustomer", Order.class).getResultList();
    }

    @Override
    public PaginatedListWrapper<Order> get(PaginatedListWrapper<Order> listWrapper) {
        listWrapper.setResultCount(count(entityManager.createQuery("SELECT COUNT(o.id) FROM Order o")));
        int start = (listWrapper.getCurrentPage() - 1) * listWrapper.getPageSize();
        listWrapper.setList(find(start, listWrapper.getPageSize(),
                entityManager.createNamedQuery("Order.findAll", Order.class)));
        return listWrapper;
    }
}

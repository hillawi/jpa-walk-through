package org.hill.jpa.model.entity;

import org.hill.jpa.model.OrderStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by Hillawi on 11-04-17.
 */
@Entity
@Table(name = "ORDER")
@NamedQueries({
        @NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o"),
        @NamedQuery(name = "Order.findAllByCustomer", query = "SELECT o FROM Order o, Customer c WHERE o.customer.id = c.id")
})
public class Order implements Serializable {
    @TableGenerator(name = "ORDER_GEN", table = "ORDER_ID_GEN", pkColumnName = "ID", valueColumnName = "VAL")
    @GeneratedValue(generator = "ORDER_GEN")
    @Column(name = "ORDER_ID")
    @Id
    private long id;
    private OrderStatus status;
    @ManyToMany
    @JoinTable(name = "ORDER_PRODUCT",
            joinColumns = @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    private Collection<Product> products;
    @Column(name = "CREATION_DATE")
    private LocalDateTime creationTime;
    @Column(name = "MODIFICATION_DATE")
    private LocalDateTime modificationTime;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(LocalDateTime modificationTime) {
        this.modificationTime = modificationTime;
    }
}

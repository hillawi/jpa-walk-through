package org.hill.jpa.bean;

import org.hill.jpa.model.entity.Order;
import org.hill.jpa.model.entity.Product;
import org.hill.jpa.service.OrderService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hillawi on 12-04-17.
 */
@ManagedBean
@SessionScoped
public class CartBean {
    @Inject
    private OrderService orderService;
    private Map<Product, Integer> products;
    private LocalDateTime creationDate;
    private Order order;
    private Product product;

    @PostConstruct
    public void init() {
        products = new HashMap<>();
        order = new Order();
        product = new Product();
        creationDate = LocalDateTime.now();
    }

    public void addProduct() {
        int count = 1;
        if (products.containsKey(product)) {
            count += products.get(product);
        }
        products.put(product, count);
    }

    public void submitCart() {
        order.setProducts(products.keySet());
        orderService.create(order);
    }

    public void cancelCart() {
        products.clear();
        init();
    }

    public Map<Product, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public List<Order> getOrders() {
        return orderService.getAllByCustomer();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

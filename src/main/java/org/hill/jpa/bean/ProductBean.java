package org.hill.jpa.bean;

import org.hill.jpa.model.entity.Product;
import org.hill.jpa.service.ProductService;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Hillawi on 12-04-17.
 */
@ManagedBean
@ViewScoped
public class ProductBean implements Serializable {
    @Inject
    private ProductService productService;
    private Product product;

    @PostConstruct
    public void init() {
        product = new Product();
    }

    public void saveProduct() {
        productService.create(product);
    }

    public void onEdit(RowEditEvent event) {
        this.product = productService.update((Product) event.getObject());
        FacesMessage msg = new FacesMessage("Product Edited", "Product #" + this.product.getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEditCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit cancelled", ("Product #" + ((Product) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Product> getProducts() {
        return productService.getAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

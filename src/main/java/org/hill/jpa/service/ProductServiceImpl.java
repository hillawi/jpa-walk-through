package org.hill.jpa.service;

import org.hill.jpa.model.PaginatedListWrapper;
import org.hill.jpa.model.entity.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Hillawi on 12-04-17.
 */
@Stateless
public class ProductServiceImpl implements ProductService {
    @PersistenceContext(unitName = "managedService")
    private EntityManager entityManager;

    public ProductServiceImpl() {
    }

    public ProductServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Product create(Product product) {
        validate(product);
        entityManager.persist(product);
        return product;
    }

    private void validate(Product product) {
    }

    @Override
    public Product get(long productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    public Product update(Product product) {
        Product updatedProduct = entityManager.merge(product);
        return updatedProduct;
    }

    @Override
    public List<Product> getAll() {
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    @Override
    public PaginatedListWrapper<Product> get(PaginatedListWrapper<Product> listWrapper) {
        listWrapper.setResultCount(count(entityManager.createQuery("SELECT COUNT(p.id) FROM Product p")));
        int start = (listWrapper.getCurrentPage() - 1) * listWrapper.getPageSize();
        listWrapper.setList(find(start, listWrapper.getPageSize(),
                entityManager.createNamedQuery("Product.findAll", Product.class)));
        return listWrapper;
    }
}

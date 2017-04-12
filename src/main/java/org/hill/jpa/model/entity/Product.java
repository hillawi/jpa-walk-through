package org.hill.jpa.model.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hill.jpa.model.ProductCategory;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Hillawi on 11-04-17.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "PRODUCT")
@NamedQueries({@NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p")})
public class Product implements Serializable {
    @Id
    @TableGenerator(name = "PRODUCT_GEN", table = "PRODUCT_ID_GEN", pkColumnName = "ID", pkColumnValue = "VAL")
    @GeneratedValue(generator = "PRODUCT_GEN")
    @Column(name = "PRODUCT_ID")
    private long id;
    @Column(name = "CAT", length = 100)
    private ProductCategory productCategory;
    @Column(name = "DESC")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product)) {
            return false;
        }
        return id == ((Product) obj).id && id != 0;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

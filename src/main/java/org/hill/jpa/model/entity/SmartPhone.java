package org.hill.jpa.model.entity;

import org.hill.jpa.model.ProductCategory;

import javax.persistence.Column;

/**
 * Created by Hillawi on 11-04-17.
 */
public class SmartPhone extends Product {
    @Column(name = "OPERATING_SYSTEM")
    private String os;

    public SmartPhone() {
        this("");
    }

    public SmartPhone(String description) {
        setProductCategory(ProductCategory.PHONE);
        setDescription(description);
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}

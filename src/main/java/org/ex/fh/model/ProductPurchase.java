/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.model;

/**
 *
 * @author dgmin
 */
public class ProductPurchase {
    private Product product;
    private int numberOfItem = 0;

    public ProductPurchase(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public void setNumberOfItem(int numberOfItem) {
        this.numberOfItem = (numberOfItem < 0) ? 0 : numberOfItem;
    }
    
    
}

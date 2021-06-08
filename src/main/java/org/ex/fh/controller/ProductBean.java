/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.ex.fh.model.Product;
import org.ex.fh.model.ProductPurchase;
import util.DbAPIBean;

/**
 *
 * @author dgmin
 */
@Named(value = "productBean")
@RequestScoped
public class ProductBean {

    @Inject
    private DbAPIBean dbAPIBean;
    
    private List<ProductPurchase> listProductPurchase;
    
    /**
     * Creates a new instance of ServiceBean
     */
    public ProductBean() {
        // do nothing
    }

    public List<ProductPurchase> getListProductPurchase() {
        listProductPurchase = new ArrayList<>();
        
        List<Product> listProduct = dbAPIBean.getListProduct();
        for(Product product : listProduct) {
            listProductPurchase.add(new ProductPurchase(product));
        }
        
        return listProductPurchase;
    }

}

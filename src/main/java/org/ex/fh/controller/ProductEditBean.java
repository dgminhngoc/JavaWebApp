/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.ex.fh.model.Product;
import util.DbAPIBean;

/**
 *
 * @author dgmin
 */
@Named("productEditBean")
@RequestScoped
public class ProductEditBean implements Serializable {

    @Inject
    private DbAPIBean dbAPIBean;
    
    private List<Product> listProduct = new ArrayList<>();    
    private Product selectedProduct;
    private boolean shouldShowBtnEdit = false;
    private Date date = new Date();
    /**
     * Creates a new instance of ServiceBean
     */
    public ProductEditBean() {
        //do nothing
    }
    
    @PostConstruct
    private void init(){
        listProduct = dbAPIBean.findListAllProduct();       
    }
   
    public List<Product> getListProduct() {         
        return listProduct;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        shouldShowBtnEdit = true;
    }

    public boolean isShouldShowBtnEdit() {
        return shouldShowBtnEdit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
   
    public void doSubmitChange() {
        selectedProduct.setProductChangeDate(date);
        boolean success = dbAPIBean.updateProduct(selectedProduct);
        
        if(success) {
            System.out.println("doSubmitChange success");
        }
        else {
            System.out.println("doSubmitChange NOT success");
        }
    }
}

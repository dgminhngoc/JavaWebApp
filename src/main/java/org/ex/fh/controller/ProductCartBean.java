/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.ex.fh.model.ProductPurchase;
import util.AppInfo;
import util.ProductCart;

/**
 *
 * @author dgmin
 */
@ManagedBean(name="productCartBean")
@SessionScoped
public class ProductCartBean implements Serializable {
    
    private int numberOfItem = 0;
    private BigDecimal totalPrice = BigDecimal.valueOf(0.0);
    private List<ProductPurchase> listProductPurchase;
    
    public ProductCartBean() {
        // do nothing
    }

    public List<ProductPurchase> getListProductPurchase() {
        listProductPurchase = ProductCart.getInstance().getListProductPurchase();
        return listProductPurchase;
    }

    public int getNumberOfItem() {
        numberOfItem = ProductCart.getInstance().getNumberOfItem();
        return numberOfItem;
    }

    public BigDecimal getTotalPrice() {
        totalPrice = ProductCart.getInstance().getTotalPrice();
        return totalPrice;
    }
    
    //user can only purchase after login
    public String confirm() {
        //user is already logged in
        if(AppInfo.getInstance().getUser() != null) {  
            return "/cart_purchase.xhtml";
        }
        //guest
        else {
            return "/login.xhtml?faces-redirect=true";
        }
    }
}
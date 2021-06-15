/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
    
    @PostConstruct
    private void init() {
        listProductPurchase = ProductCart.getInstance().getListProductPurchase();
        
        for(ProductPurchase productPurchase : listProductPurchase) {
            System.out.println("product: "+productPurchase.getProduct().getProductName());
            System.out.println("price: "+productPurchase.getProduct().getProductPrice());
            System.out.println("number: "+productPurchase.getNumberOfItem());
            numberOfItem += productPurchase.getNumberOfItem();
            totalPrice = totalPrice.add(productPurchase.getProduct().getProductPrice()
                    .multiply(BigDecimal.valueOf(productPurchase.getNumberOfItem())));
        }
        
    }

    public List<ProductPurchase> getListProductPurchase() {
        return listProductPurchase;
    }

    public int getNumberOfItem() {
        return numberOfItem;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    
    public String purchase() {
        //user is already logged in
        if(AppInfo.getInstance().getUser() != null) {
            ProductCart.getInstance().purchase();
            
            String message = "Purchase successful";
            FacesMessage fmsg = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage("halloForm", fmsg);
            
            return "/home.xhtml";
        }
        //guest
        else {
            return "/login.xhtml";
        }
    }
}
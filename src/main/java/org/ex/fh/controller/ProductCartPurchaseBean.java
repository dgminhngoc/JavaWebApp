/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.ex.fh.model.ProductPurchase;
import util.DbAPIBean;
import util.ProductCart;

/**
 *
 * @author dgmin
 */
@ManagedBean(name="productCartPurchaseBean")
@SessionScoped
public class ProductCartPurchaseBean implements Serializable {
    
    @Inject
    private DbAPIBean dbAPIBean;
    
    private int numberOfItem = 0;
    private BigDecimal totalPrice = BigDecimal.valueOf(0.0);
    private List<ProductPurchase> listProductPurchase;
    
    public ProductCartPurchaseBean() {
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
    
    public String purchase() {
        boolean success = dbAPIBean.insertPurchase(listProductPurchase);
        
        String message;
        if(success) {
            message = "Purchase successful";
            listProductPurchase.clear();
        }
        else {
            message = "Purchase failed, please try again";
        }
        FacesMessage fmsg = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage("halloForm", fmsg);

        return "/home.xhtml"; 
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.ex.fh.model.Product;
import org.ex.fh.model.ProductCategory;
import org.ex.fh.model.ProductPurchase;
import util.DbAPIBean;
import util.ProductCart;

/**
 *
 * @author dgmin
 */
@ManagedBean(name="productBean")
@SessionScoped
public class ProductBean implements Serializable {

    @Inject
    private DbAPIBean dbAPIBean;
    
    private List<ProductPurchase> listProductPurchase = new ArrayList<>();
    private List<ProductCategory> listProductCategory = new ArrayList<>();;
    
    private List<String> listStrCategory = new ArrayList<>();
    private String selectedStrCategory;
    private ProductCategory selectedCategory;
    /**
     * Creates a new instance of ServiceBean
     */
    public ProductBean() {
        //do nothing
    }
    
    @PostConstruct
    private void init(){
        listProductCategory = dbAPIBean.getListProductCategory();
        
        for(ProductCategory productCategory : getListProductCategory()) {
            listStrCategory.add(productCategory.getProductCatName());
        }
        
        selectedCategory = listProductCategory.get(0);
    }
   
    public List<ProductPurchase> getListProductPurchase() {
        listProductPurchase.clear();
        
        List<Product> listProduct = dbAPIBean.findListProduct(getSelectedProductCategory());
        for(Product product : listProduct) {
            listProductPurchase.add(new ProductPurchase(product));
        }
          
        return listProductPurchase;
    }
    
    private ProductCategory getSelectedProductCategory() {
        for(ProductCategory productCategory : listProductCategory) {
            if(productCategory.getProductCatName().equals(selectedStrCategory)) {
                selectedCategory = productCategory;
                break;
            }
        }
        
        return selectedCategory;
    }
    
    public void reloadData(){
        for(ProductCategory productCategory : listProductCategory) {
            if(productCategory.getProductCatName().equals(selectedStrCategory)) {
                selectedCategory = productCategory;
                break;
            }
        }
    }
    
    public void addToCart() {
        ProductCart.getInstance().addToCart(listProductPurchase);
        listProductCategory.clear();
    }

    public List<ProductCategory> getListProductCategory() { 
        return listProductCategory;
    }
    
    public List<String> getListStrCategory() {
        return listStrCategory;
    }

    public String getSelectedStrCategory() {
        return selectedStrCategory;
    }

    public void setSelectedStrCategory(String selectedStrCategory) {
        this.selectedStrCategory = selectedStrCategory;
    }
}

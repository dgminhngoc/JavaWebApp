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
import org.ex.fh.model.ProductCategory;
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
    private Date editDate = new Date();
    
    private List<ProductCategory> listProductCategory = new ArrayList<>();
    private List<String> listStrCategory = new ArrayList<>();
    private String selectedStrCategory;
    /**
     * Creates a new instance of ServiceBean
     */
    public ProductEditBean() {
        //do nothing
    }
    
    @PostConstruct
    private void init(){
        listProduct = dbAPIBean.findListAllProduct();  
        listProductCategory = dbAPIBean.getListProductCategory();
        for(ProductCategory productCategory : listProductCategory) {
            listStrCategory.add(productCategory.getProductCatName());
        }
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

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date date) {
        this.editDate = date;
    }

    public List<String> getListStrCategory() {
        if(selectedProduct != null) {
            setSelectedStrCategory(selectedProduct.getFkProductCateId().getProductCatName());
        }
        
        return listStrCategory;
    }

    public void setListStrCategory(List<String> listStrCategory) {
        this.listStrCategory = listStrCategory;
    }

    public String getSelectedStrCategory() {
        return selectedStrCategory;
    }

    public void setSelectedStrCategory(String selectedStrCategory) {
        this.selectedStrCategory = selectedStrCategory;
    }
   
    public String doSubmitChange() {
        ProductCategory selectedCategory = selectedProduct.getFkProductCateId();
        for(ProductCategory productCategory : listProductCategory) {
            if(productCategory.getProductCatName().equals(selectedStrCategory)) {
                selectedCategory = productCategory;
                break;
            }
        }
        
        selectedProduct.setProductChangeDate(editDate);
        selectedProduct.setFkProductCateId(selectedCategory);
        
        boolean success = dbAPIBean.updateProduct(selectedProduct);
        if(success) {
            System.out.println("doSubmitChange success");
            
            return "product_edit?faces-redirect=true";
        }
        else {
            System.out.println("doSubmitChange NOT success");
            return "product_edit";
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.ex.fh.model.ProductPurchase;

/**
 *
 * @author dgmin
 */
public class ProductCart {
    
    @Inject
    private DbAPIBean dbAPIBean;
    
    private static ProductCart instance;
    
    private List<ProductPurchase> listProductPurchase = new ArrayList<>();
    
    private ProductCart(){}
    
    public static ProductCart getInstance() {
        if(instance == null) {
            instance = new ProductCart();
        }
        
        return instance;
    }
    
    public void addToCart(List<ProductPurchase> newListProductPurchase) {
        if(newListProductPurchase != null) {
            for(ProductPurchase newProductPurchase : newListProductPurchase) {  
                if(newProductPurchase.getNumberOfItem() == 0) {
                    break;
                }
                
                ProductPurchase foundProductPurchase = null;

                for(ProductPurchase productPurchase : listProductPurchase) {
                    if(productPurchase.getProduct().getProductId()
                            .equals(newProductPurchase.getProduct().getProductId())) {

                        foundProductPurchase = productPurchase;
                        break;
                    }
                }

                if(foundProductPurchase != null) {
                    int newAmount = foundProductPurchase.getNumberOfItem() 
                                + newProductPurchase.getNumberOfItem();
                        foundProductPurchase.setNumberOfItem(newAmount);
                }
                else {
                    listProductPurchase.add(newProductPurchase);
                }
            }
        }
    }
    
    public void clearCart() {
        listProductPurchase.clear();
    }
    
    public void purchase() {
        dbAPIBean.uploadBill(listProductPurchase);
        listProductPurchase.clear();
    }

    public List<ProductPurchase> getListProductPurchase() {
        return listProductPurchase;
    }
   
}

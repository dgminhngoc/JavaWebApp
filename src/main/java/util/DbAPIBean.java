/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.ex.fh.model.Account;
import org.ex.fh.model.Product;
import org.ex.fh.model.ProductCategory;
import org.ex.fh.model.User;

/**
 *
 * @author dgmin
 */
@Named(value = "dbAPIBean")
@Dependent
public class DbAPIBean {
        
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    public List<Product> findListProduct(ProductCategory productCategory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Product> query = entityManager
                .createNamedQuery("Product.findByFkProductCateId", Product.class);
        query.setParameter("fkProductCateId", productCategory.getProductCatId());
        List<Product> listProduct = query.getResultList();
        return listProduct;
    }
    
    public List<ProductCategory> getListProductCategory() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<ProductCategory> query = entityManager
                .createNamedQuery("ProductCategory.findAll", ProductCategory.class);
        List<ProductCategory> listProductCategory = query.getResultList();
        return listProductCategory;
    }
    
    public Account findAccount(String username) {
        Account account = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
        
            TypedQuery<Account> query =entityManager
                    .createNamedQuery("Account.findByAccName", Account.class);
            query.setParameter("accName", username);

            account = query.getSingleResult();
            
            //account erhalten
        } 
        catch (Exception e) {
            //es gibt keinen Account für diesen Username
        }
        
        return account;
    }
    
    public User findUser(Account account) {
        User user = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
        
            TypedQuery<User> query =
                    entityManager.createNamedQuery("User.findByFkAccId", User.class);
            query.setParameter("fkAccId", account.getAccId());
            
            user = query.getSingleResult();
            
            //account erhalten
        } 
        catch (Exception e) {
            //es gibt keinen Account für diesen Username
        }
        
        return user;
    }
}

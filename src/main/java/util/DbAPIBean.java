/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import org.ex.fh.model.Account;
import org.ex.fh.model.Bill;
import org.ex.fh.model.BillDetail;
import org.ex.fh.model.Product;
import org.ex.fh.model.ProductCategory;
import org.ex.fh.model.ProductPurchase;
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
    
    @Resource
    private UserTransaction userTransaction; 
    
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
    
    public Bill findBill(Date date){
        Bill bill = null;
        
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
        
            TypedQuery<Bill> query =
                    entityManager.createNamedQuery("Bill.findByBillDate", Bill.class);
            query.setParameter("billDate", date);
            
            bill = query.getSingleResult();
            
            //account erhalten
        } 
        catch (Exception e) {
            //es gibt keinen Account für diesen Username
        }
        
        return bill;
    }
    
    public void insertPurchase(List<ProductPurchase> listProductPurchase) {
        if (listProductPurchase != null && listProductPurchase.size() > 0) {
            Date date = new Date();
            Bill bill = new Bill(date, AppInfo.getInstance().getUser().getUserId());
            try {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                userTransaction.begin();
                entityManager.joinTransaction();
                entityManager.persist(bill);
                for(ProductPurchase productPurchase : listProductPurchase) {       
                    entityManager.persist(new BillDetail(productPurchase.getNumberOfItem(), 
                        bill.getBillId(), 
                        productPurchase.getProduct().getProductId(), 
                        date));
                }
                userTransaction.commit();      
            } 
            catch (Exception e) {
                try {            
                userTransaction.rollback();
                } 
                catch (Exception ex) {
                    //do nothing
                }
            }
        }
    }
    
    public boolean insertRegisterData(Account account, User user) {
        boolean success = false;
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {           
            userTransaction.begin( );          
            entityManager.joinTransaction();
            entityManager.persist(account);
            user.setFkAccId(account);
            entityManager.persist(user);
            userTransaction.commit( );
           
            return true;
        } 
        catch (Exception e) {
            System.out.println("insertRegisterData error: " + e.toString());
            try {            
                userTransaction.rollback();
            } 
            catch (Exception ex) {
                //do nothing
            }
        }
        finally{
            entityManager.close();
        }
      
        return false;
    } 
}
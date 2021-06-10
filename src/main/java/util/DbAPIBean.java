/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Date;
import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.ex.fh.model.Account;
import org.ex.fh.model.Bill;
import org.ex.fh.model.BillDetail;
import org.ex.fh.model.Product;
import org.ex.fh.model.ProductCategory;
import org.ex.fh.model.ProductPurchase;
import org.ex.fh.model.RegisterData;
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
           insertBill(new Bill(date, AppInfo.getInstance().getUser().getUserId()));
           Bill bill = findBill(date);
           if(bill != null) {
               insertBillDetail(bill,listProductPurchase);
           }
        }
    }
    
    private void insertBill(Bill bill) {      
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(bill);
        entityManager.getTransaction().commit();
        entityManager.close();  
    }
    
    private void insertBillDetail(Bill bill, List<ProductPurchase> listProductPurchase) {
        if (listProductPurchase!= null && listProductPurchase.size() > 0) {
            for(ProductPurchase productPurchase : listProductPurchase) {
                EntityManager entityManager = entityManagerFactory.createEntityManager();
                entityManager.getTransaction().begin();
                entityManager.persist(new BillDetail(productPurchase.getNumberOfItem(), 
                        bill.getBillId(), 
                        productPurchase.getProduct().getProductId(), 
                        bill.getBillDate()));
                entityManager.getTransaction().commit();
                entityManager.close();
            }
        }
    }
    
    public void insertRegisterData(RegisterData data) {
        if (data != null) {
            insertAccount(new Account(data.getUsername(), data.getPassword()));
            Account account = findAccount(data.getUsername());
            //insert account successful
            if (account != null && account.getAccName().equals(data.getUsername())) {
                insertUser(new User(data.getFirstName(), 
                        data.getLastName(), 
                        data.getTelNumber(), 
                        data.getEmail(), 
                        account.getAccId()));

                User user = findUser(account);
                //insert user successful
                if (user != null && user.getUserLastName().equals(data.getLastName())) {
                    //notify success
                }
                // insert user error !!!
                else {
                    //notify error
                    removeAccount(account);
                }
            }
            // insert account error !!!
            else {
                //notify error
            }
        }
    }
    
    private void removeAccount(Account account) {
        if(account != null) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            Account rmAccount = entityManager.find(Account.class, account.getAccId());
            entityManager.remove(rmAccount);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
    
    private void insertAccount(Account account) {
        if(account != null) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }
    
    private void insertUser(User user){
        if(user != null) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    } 
}

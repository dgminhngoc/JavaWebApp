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
import org.ex.fh.model.User;

/**
 *
 * @author dgmin
 */
@Named(value = "dbAPIBean")
@Dependent
public class DbAPIBean {
    
    private List<Product> listService;
    
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    public List<Product> getListProduct() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findall", Product.class);
        listService = query.getResultList();
        return listService;
    }
    
    public Account findAccount(String username) {
        Account account = null;
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
        
            TypedQuery<Account> query =
                    entityManager.createQuery("SELECT s FROM Account a "
                    + "WHERE a.ACC_NAME = :username", Account.class);
            query.setParameter("username", username);

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
                    entityManager.createQuery("SELECT s FROM User user "
                    + "WHERE user.FK_ACC_ID = :acc_id", User.class);
            query.setParameter("acc_id", account.getAccId());

            user = query.getSingleResult();
            
            //account erhalten
        } 
        catch (Exception e) {
            //es gibt keinen Account für diesen Username
        }
        
        return user;
    }
}

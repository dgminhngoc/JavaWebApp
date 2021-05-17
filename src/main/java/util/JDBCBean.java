package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import org.ex.fh.model.Account;
import org.ex.fh.model.Service;
import org.ex.fh.model.User;

/**
 *
 * @author dgmin
 */
@Named(value = "jDBCBean")
@Dependent
public class JDBCBean {

    private final   String      driver      = "org.mariadb.jdbc.Driver";
    private final   String      dbURL       = "jdbc:mariadb://localhost:3306/praktikum";
    private         Connection  connection  = null;
    
    private static final String TABLE_ACCOUNT   = "account";
    private static final String TABLE_USER      = "user";
    
    private List<Service> listService = new ArrayList<>();
    
    private Logger logger = Logger.getLogger(JDBCBean.class.getName());
    /**
     * Creates a new instance of JDBCBean
     */
    public JDBCBean() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbURL, "dba", "dba");
        } 
        catch (ClassNotFoundException ex) {
            logger.info("JDBC Connection is created successfully");
        } 
        catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public void getListServiceFromJDBC() {
        String sql = "SELECT * FROM service ORDER BY SERVICE_NAME";
        
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                //listService.add(new Service());
            }
        } 
        catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Service> getListService() {
        return listService;
    }

    public void setListService(List<Service> listService) {
        this.listService = listService;
    }
    
    public Account getAccountFromJDBC(String username, String password){
        String sql = String.format("SELECT * FROM %s WHERE ACC_NAME=\"%s\"",
                TABLE_ACCOUNT, username);
        List<Account> listAccount = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                listAccount.add(new Account(
                    result.getInt("ACC_ID"),
                    result.getString("ACC_NAME"),
                    result.getString("ACC_PASSWORD")
                ));
            }
        } 
        catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        if (listAccount.isEmpty()) {
            return null;
        }
        else {
            return listAccount.get(0);
        }
    }
    
    public User getUserFromJDBC(int accountId){
        String sql = String.format("SELECT * FROM %s WHERE FK_ACC_ID=%d",
                TABLE_USER, accountId);
        List<User> listUser = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                listUser.add(new User(
                    result.getInt("USER_ID"),
                    result.getString("USER_FIRST_NAME"),
                    result.getString("USER_LAST_NAME"),
                    result.getString("USER_TEL_NR"),
                    result.getString("USER_EMAIL"),
                    result.getInt("FK_ACC_ID")
                ));
            }
        } 
        catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        if (listUser.isEmpty()) {
            return null;
        }
        else {
            return listUser.get(0);
        }
    }
    
    public User checkAccountValid(String username, String password){
        String sql = String.format("SELECT * FROM %s "
                + "WHERE ACC_NAME=\"%s\" AND ACC_PASSWORD=\"%s\"",
                TABLE_ACCOUNT, username, password);
        List<Account> listAccount = new ArrayList<>();
        try {
            ResultSet result = connection.createStatement().executeQuery(sql);
            while(result.next()) {
                listAccount.add(new Account(
                    result.getInt("ACC_ID"),
                    result.getString("ACC_NAME"),
                    result.getString("ACC_PASSWORD")
                ));
            }
        } 
        catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        if (listAccount.isEmpty()) {
            return null;
        }
        else {
            return getUserFromJDBC(listAccount.get(0).getAccId());
        }
    }
    
    public void createAccountAndUser(User user, Account account) {
        createAccount(account);
        Account dbAccount = getAccountFromJDBC(account.getAccName(), account.getAccPassword());
        
        if (dbAccount != null) {
            user.setFkAccId(dbAccount.getAccId());
            createUser(user);
        }
    }
    
    private void createAccount(Account account){
        String sqlCreateAccount = String.format("INSERT INTO %s (ACC_NAME, ACC_PASSWORD)"
                + " VALUES (\'%s\',\'%s\')",
                TABLE_ACCOUNT, account.getAccName(), account.getAccPassword());
    
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlCreateAccount);
            System.out.println("Eingefügte Datensätze: " + result);
        } 
        catch (SQLException ex) {
            String exception = ex.getMessage();
            Logger.getLogger(JDBCBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
    
    private void createUser(User user) {
    String sqlCreateUser = String.format("INSERT INTO %s ("
                + "USER_FIRST_NAME, "
                + "USER_LAST_NAME, "
                + "USER_TEL_NR, "
                + "USER_EMAIL,"
                + "FK_ACC_ID "
                + ")"
                + " VALUES (\'%s\',\'%s\',\'%s\',\'%s\',%d)",
                TABLE_USER, user.getUserFirstName(), user.getUserLastName(),
                user.getUserTelNr(), user.getUserEmail(), user.getFkAccId());
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlCreateUser);
            System.out.println("Eingefügte Datensätze: " + result);
        } 
        catch (SQLException ex) {
            String exception = ex.getMessage();
            Logger.getLogger(JDBCBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        }
}

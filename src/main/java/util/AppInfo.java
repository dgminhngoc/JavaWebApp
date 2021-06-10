/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import org.ex.fh.model.Account;
import org.ex.fh.model.User;

/**
 *
 * @author dgmin
 */
public class AppInfo {
    private static AppInfo instance;
    
    private User user;
    private Account account;

    private AppInfo(){}
    
    public static AppInfo getInstance() {
        if(instance == null) {
            instance = new AppInfo();
        }
        
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
    public void clearData() {
        user = null;
        account = null;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.ex.fh.model.Account;
import org.ex.fh.model.User;
import util.DbAPIBean;

/**
 *
 * @author dgmin
 */
@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
    @Inject
    private DbAPIBean dbAPIBean;
    @Inject
    private Account account;
    @Inject
    private User user;
    
    public RegisterBean() {
        // do nothing
    }
    
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String doRegister(){
        System.out.println("doRegister");
        boolean success = dbAPIBean.insertRegisterData(account, user);
        
        return success ? "/login.xhtml" : "/start.xhtml";  
    }
}
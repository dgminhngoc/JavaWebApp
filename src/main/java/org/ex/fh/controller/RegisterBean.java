/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.ex.fh.model.Account;
import org.ex.fh.model.User;
import util.JDBCBean;

/**
 *
 * @author dgmin
 */
@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
    
    private String username;
    private String password;
    
    private String firstName;
    private String lastName;
    private String telNumber;
    private String email;
    
    private JDBCBean jdbcBean;
    
    public RegisterBean() {
        jdbcBean = new JDBCBean();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String doRegister(){
        Account account = jdbcBean.getAccountFromJDBC(username, password);
        
        //account is already created
        if (account != null) {
            String message = "Das Konto ist bereits verfügbar";
            FacesMessage fmsg = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage("startForm", fmsg);
            
            return "/start.xhtml";
        }
        //account doesnt exist, should be created
        else {
            account = new Account();
            account.setAccName(username);
            account.setAccPassword(password);
            
            User user = new User();
            user.setUserFirstName(firstName);
            user.setUserLastName(lastName);
            user.setUserTelNr(telNumber);
            user.setUserEmail(email);
            
            jdbcBean.createAccountAndUser(user, account);
            
            return "/login.xhtml";
        }   
    }
}
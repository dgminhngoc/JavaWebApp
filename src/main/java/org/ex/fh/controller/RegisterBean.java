/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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
    
    public void validateName(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
        String name = (String)value;
        FacesMessage msg;
        String pattern;
        
        pattern = "[a-zA-z]+([ '-][a-zA-Z]+)*"; // Nur Buchstaben und -
                
        if(!Pattern.matches(pattern, name)){  
            msg = new FacesMessage("Bitte geben Sie einen gültigen Namen ein"); 
            context.addMessage(component.getClientId(context), msg);
            throw new ValidatorException(msg);
        }    
    }
      
    public void validateTelNumber(FacesContext context, UIComponent component, Object value) 
          throws ValidatorException {
        String telefonnr = (String)value;
        FacesMessage msg;
        String pattern;

        pattern =  "^\\++\\d{7,15}$";   // Die Nummer muss mit einem + starten und zwischen 7-15
                                        // Ziffern enthalten.
        if(!Pattern.matches(pattern, telefonnr)){
            msg = new FacesMessage("Bitte geben Sie Ihre Nummer im Format +49... ein"); 
            context.addMessage(component.getClientId(context), msg);
            throw new ValidatorException(msg);
        } 
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.ex.fh.model.Account;
import org.ex.fh.model.User;
import util.AppInfo;
import util.DbAPIBean;
import util.JSPUtil;

/**
 *
 * @author dgmin
 */
@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private String username;
    private String password;
    private String message;
        
    @Inject
    private DbAPIBean dbAPIBean;
    
    public LoginBean() {
        // do nothing
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String doLogin(){
        System.out.println("LoginBean");
        
        boolean isAccountValid = false;
        Account account = dbAPIBean.findAccount(username);
        if (account != null && account.getAccId() != null) {
            if(account.getAccName().equals(username)
                    && account.getAccPassword().equals(password)) {
                isAccountValid = true;
            }
        }
       
        if(isAccountValid) {
            User user = dbAPIBean.findUser(account);
            
            AppInfo.getInstance().setAccount(account);
            AppInfo.getInstance().setUser(user);
           
            String message = String.format("Hallo Benutzer\n%s!"
                     + "\nWir haben heute tolle Angebote!", user.getUserLastName());
            FacesMessage fmsg = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage("halloForm", fmsg);

            if(account.getAccIsAdmin()) {
                return "/home_admin.xhtml";
            }
            else {
                return "/home.xhtml";
            }

        }
        else {
            JSPUtil.addErrorMessageForComponent("LOG", "Überprüfen Sie Ihre Login-Daten!");
            
            return "/login.xhtml";
        }
    }
    
    public String doLogout(){
        AppInfo.getInstance().clearData();
                
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.getExternalContext().invalidateSession();
        facesContext.addMessage("startForm", 
                new FacesMessage(FacesMessage.SEVERITY_INFO, 
                        "Auf Wiedersehen!", "Bis zum nächsten Mal!"));
        
        return "/start.xhtml";
    }
}
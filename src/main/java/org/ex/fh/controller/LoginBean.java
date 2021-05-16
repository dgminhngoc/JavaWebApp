/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.ex.fh.model.User;
import util.JDBCBean;

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
    
    private JDBCBean jdbcBean;
    
    public LoginBean() {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String doLogin(){
        User user = null;
        
        if (jdbcBean != null) {
             user = jdbcBean.checkAccountValid(username, password);
             
             if (user != null) {
                 return "/hallo.xhtml";
             }
        }
        
        return "/start.xhtml";
    }
    
    public String doLogout(){
        return "/start.xhtml";
    }
}
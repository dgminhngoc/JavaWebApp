/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.ex.fh.model.Account;
import org.ex.fh.model.RegisterData;
import org.ex.fh.model.User;
import util.DbAPIBean;

/**
 *
 * @author dgmin
 */
@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean implements Serializable {
    
    private RegisterData data;
    
    @Inject
    private DbAPIBean dbAPIBean;
    
    public RegisterBean() {
        // do nothing
    }
    
    @PostConstruct
    private void init(){
        data = new RegisterData();
    }

    public RegisterData getData() {
        return data;
    }

    public void setData(RegisterData data) {
        this.data = data;
    }

    public String doRegister(){
        dbAPIBean.insertRegisterData(data);
        return "/login.xhtml";  
    }
}
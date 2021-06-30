/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import util.AppInfo;
/**
 *
 * @author dgmin
 */
@ManagedBean(name="navigateBean")
@SessionScoped
public class NavigateBean implements Serializable {
    
    public NavigateBean() {
        // do nothing
    }

    public String navigateToLoginScreen() {
        return "/login.xhtml";
    }
    
    public String navigateToStartScreen() {
        if(AppInfo.getInstance().getUser() == null) {
            return "/start.xhtml";
        }
        //if user is logged-in, he must be navigate to home screen
        else {
            //only admin can see edit option
            if(AppInfo.getInstance().getAccount().getAccIsAdmin()) {
                return "/home_admin.xhtml";
            }
            else {
                return "/home.xhtml";
            }         
        } 
    }
    
    public String navigateToHomeScreen() {
        return "/home.xhtml";
    }
    
    public String navigateToCartScreen() {
        return "/cart.xhtml";
    }
    
    public String navigateToProductScreen() {
        return "/product.xhtml";
    }
    
    public String navigateToProductEditScreen() {
        return "/product_edit.xhtml";
    }
    
    public String navigateToRegisterScreen() {
        return "/register.xhtml";
    }
}
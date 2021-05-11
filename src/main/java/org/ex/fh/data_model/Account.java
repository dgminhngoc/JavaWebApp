/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.data_model;

/**
 *
 * @author dgmin
 */
public class Account {
    
    private int     id;
    private String  name;
    private String  password;

    public Account(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return name;
    }

    public void setAccountName(String name) {
        this.name = name;
    }

    public String getAccountPassword() {
        return password;
    }

    public void setAccountPassword(String password) {
        this.password = password;
    }
}

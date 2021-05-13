/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.io.Serializable;

/**
 *
 * @author dgmin
 */
public abstract class RegisterBean implements Serializable{
    abstract void register(String username, String password);
}

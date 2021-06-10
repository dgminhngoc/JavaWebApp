/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dgmin
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByUserFirstName", query = "SELECT u FROM User u WHERE u.userFirstName = :userFirstName"),
    @NamedQuery(name = "User.findByUserLastName", query = "SELECT u FROM User u WHERE u.userLastName = :userLastName"),
    @NamedQuery(name = "User.findByUserTelNr", query = "SELECT u FROM User u WHERE u.userTelNr = :userTelNr"),
    @NamedQuery(name = "User.findByUserEmail", query = "SELECT u FROM User u WHERE u.userEmail = :userEmail"),
    @NamedQuery(name = "User.findByFkAccId", query = "SELECT u FROM User u WHERE u.fkAccId = :fkAccId")})
public class User implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_FIRST_NAME")
    private String userFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USER_LAST_NAME")
    private String userLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "USER_TEL_NR")
    private String userTelNr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USER_EMAIL")
    private String userEmail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_ACC_ID")
    private int fkAccId;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USER_ID")
    private Integer userId;

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }
    
    public User(String userFirstName, String userLastName, String userTelNr, String userEmail, int fkAccId) {
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userTelNr = userTelNr;
        this.userEmail = userEmail;
        this.fkAccId = fkAccId;
    }

    public User(Integer userId, String userFirstName, String userLastName, String userTelNr, String userEmail, int fkAccId) {
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userTelNr = userTelNr;
        this.userEmail = userEmail;
        this.fkAccId = fkAccId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserTelNr() {
        return userTelNr;
    }

    public void setUserTelNr(String userTelNr) {
        this.userTelNr = userTelNr;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getFkAccId() {
        return fkAccId;
    }

    public void setFkAccId(int fkAccId) {
        this.fkAccId = fkAccId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.User[ userId=" + userId + " ]";
    }
}

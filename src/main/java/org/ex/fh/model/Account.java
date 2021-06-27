/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dgmin
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccId", query = "SELECT a FROM Account a WHERE a.accId = :accId"),
    @NamedQuery(name = "Account.findByAccName", query = "SELECT a FROM Account a WHERE a.accName = :accName"),
    @NamedQuery(name = "Account.findByAccPassword", query = "SELECT a FROM Account a WHERE a.accPassword = :accPassword")})
public class Account implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACC_NAME")
    private String accName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ACC_PASSWORD")
    private String accPassword;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACC_IS_ADMIN")
    private boolean accIsAdmin;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ACC_ID")
    private Integer accId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkAccId")
    private Collection<User> userCollection;
    @JoinColumn(name = "ACC_ID", referencedColumnName = "FK_ACC_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private User user;

    public Account() {
    }

    public Account(Integer accId) {
        this.accId = accId;
    }

    public Account(Integer accId, String accName, String accPassword) {
        this.accId = accId;
        this.accName = accName;
        this.accPassword = accPassword;
    }

    public Integer getAccId() {
        return accId;
    }

    public void setAccId(Integer accId) {
        this.accId = accId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getAccPassword() {
        return accPassword;
    }

    public void setAccPassword(String accPassword) {
        this.accPassword = accPassword;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accId != null ? accId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accId == null && other.accId != null) || (this.accId != null && !this.accId.equals(other.accId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.Account[ accId=" + accId + " ]";
    }
   
    public boolean getAccIsAdmin() {
        return accIsAdmin;
    }

    public void setAccIsAdmin(boolean accIsAdmin) {
        this.accIsAdmin = accIsAdmin;
    }
}

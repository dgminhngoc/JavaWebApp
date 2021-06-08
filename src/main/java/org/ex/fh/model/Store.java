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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "store")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s"),
    @NamedQuery(name = "Store.findByStoreId", query = "SELECT s FROM Store s WHERE s.storeId = :storeId"),
    @NamedQuery(name = "Store.findByStorePosPlz", query = "SELECT s FROM Store s WHERE s.storePosPlz = :storePosPlz"),
    @NamedQuery(name = "Store.findByStorePosAddr", query = "SELECT s FROM Store s WHERE s.storePosAddr = :storePosAddr"),
    @NamedQuery(name = "Store.findByStorePosCity", query = "SELECT s FROM Store s WHERE s.storePosCity = :storePosCity")})
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "STORE_ID")
    private Integer storeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "STORE_POS_PLZ")
    private String storePosPlz;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "STORE_POS_ADDR")
    private String storePosAddr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "STORE_POS__CITY")
    private String storePosCity;

    public Store() {
    }

    public Store(Integer storeId) {
        this.storeId = storeId;
    }

    public Store(Integer storeId, String storePosPlz, String storePosAddr, String storePosCity) {
        this.storeId = storeId;
        this.storePosPlz = storePosPlz;
        this.storePosAddr = storePosAddr;
        this.storePosCity = storePosCity;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStorePosPlz() {
        return storePosPlz;
    }

    public void setStorePosPlz(String storePosPlz) {
        this.storePosPlz = storePosPlz;
    }

    public String getStorePosAddr() {
        return storePosAddr;
    }

    public void setStorePosAddr(String storePosAddr) {
        this.storePosAddr = storePosAddr;
    }

    public String getStorePosCity() {
        return storePosCity;
    }

    public void setStorePosCity(String storePosCity) {
        this.storePosCity = storePosCity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (storeId != null ? storeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Store)) {
            return false;
        }
        Store other = (Store) object;
        if ((this.storeId == null && other.storeId != null) || (this.storeId != null && !this.storeId.equals(other.storeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.Store[ storeId=" + storeId + " ]";
    }
    
}

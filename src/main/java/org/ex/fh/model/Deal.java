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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "deal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Deal.findAll", query = "SELECT d FROM Deal d"),
    @NamedQuery(name = "Deal.findByDealId", query = "SELECT d FROM Deal d WHERE d.dealId = :dealId"),
    @NamedQuery(name = "Deal.findByDealStatus", query = "SELECT d FROM Deal d WHERE d.dealStatus = :dealStatus"),
    @NamedQuery(name = "Deal.findByDealValue", query = "SELECT d FROM Deal d WHERE d.dealValue = :dealValue")})
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DEAL_ID")
    private Integer dealId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "DEAL_STATUS")
    private String dealStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DEAL_VALUE")
    private int dealValue;
    @JoinColumn(name = "FK_PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    @ManyToOne(optional = false)
    private Product fkProductId;

    public Deal() {
    }

    public Deal(Integer dealId) {
        this.dealId = dealId;
    }

    public Deal(Integer dealId, String dealStatus, int dealValue) {
        this.dealId = dealId;
        this.dealStatus = dealStatus;
        this.dealValue = dealValue;
    }

    public Integer getDealId() {
        return dealId;
    }

    public void setDealId(Integer dealId) {
        this.dealId = dealId;
    }

    public String getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }

    public int getDealValue() {
        return dealValue;
    }

    public void setDealValue(int dealValue) {
        this.dealValue = dealValue;
    }

    public Product getFkProductId() {
        return fkProductId;
    }

    public void setFkProductId(Product fkProductId) {
        this.fkProductId = fkProductId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealId != null ? dealId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Deal)) {
            return false;
        }
        Deal other = (Deal) object;
        if ((this.dealId == null && other.dealId != null) || (this.dealId != null && !this.dealId.equals(other.dealId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.Deal[ dealId=" + dealId + " ]";
    }
    
}

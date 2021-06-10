/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dgmin
 */
@Entity
@Table(name = "bill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b"),
    @NamedQuery(name = "Bill.findByBillId", query = "SELECT b FROM Bill b WHERE b.billId = :billId"),
    @NamedQuery(name = "Bill.findByBillDate", query = "SELECT b FROM Bill b WHERE b.billDate = :billDate"),
    @NamedQuery(name = "Bill.findByFkUserId", query = "SELECT b FROM Bill b WHERE b.fkUserId = :fkUserId")})
public class Bill implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_DATE")
    @Temporal(TemporalType.DATE)
    private Date billDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_USER_ID")
    private int fkUserId;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_ID")
    private Integer billId;

    public Bill() {
    }

    public Bill(Integer billId) {
        this.billId = billId;
    }
    
    public Bill(Date billDate, int fkUserId) {
        this.billDate = billDate;
        this.fkUserId = fkUserId;
    }

    public Bill(Integer billId, Date billDate, int fkUserId) {
        this.billId = billId;
        this.billDate = billDate;
        this.fkUserId = fkUserId;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public int getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(int fkUserId) {
        this.fkUserId = fkUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billId != null ? billId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bill)) {
            return false;
        }
        Bill other = (Bill) object;
        if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.Bill[ billId=" + billId + " ]";
    }
}

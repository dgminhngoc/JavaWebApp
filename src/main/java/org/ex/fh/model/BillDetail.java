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
@Table(name = "bill_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BillDetail.findAll", query = "SELECT b FROM BillDetail b"),
    @NamedQuery(name = "BillDetail.findByBillDetailId", query = "SELECT b FROM BillDetail b WHERE b.billDetailId = :billDetailId"),
    @NamedQuery(name = "BillDetail.findByFkBillId", query = "SELECT b FROM BillDetail b WHERE b.fkBillId = :fkBillId"),
    @NamedQuery(name = "BillDetail.findByFkServiceId", query = "SELECT b FROM BillDetail b WHERE b.fkServiceId = :fkServiceId"),
    @NamedQuery(name = "BillDetail.findByBildDetailDate", query = "SELECT b FROM BillDetail b WHERE b.bildDetailDate = :bildDetailDate")})
public class BillDetail implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_BILL_ID")
    private int fkBillId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_PRODUCT_ID")
    private int fkProductId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILD_DETAIL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bildDetailDate;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "BILL_DETAIL_ID")
    private Integer billDetailId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_SERVICE_ID")
    private int fkServiceId;

    public BillDetail() {
    }

    public BillDetail(Integer billDetailId) {
        this.billDetailId = billDetailId;
    }

    public BillDetail(Integer billDetailId, int fkBillId, int fkServiceId, Date bildDetailDate) {
        this.billDetailId = billDetailId;
        this.fkBillId = fkBillId;
        this.fkServiceId = fkServiceId;
        this.bildDetailDate = bildDetailDate;
    }

    public Integer getBillDetailId() {
        return billDetailId;
    }

    public void setBillDetailId(Integer billDetailId) {
        this.billDetailId = billDetailId;
    }

    public int getFkBillId() {
        return fkBillId;
    }

    public void setFkBillId(int fkBillId) {
        this.fkBillId = fkBillId;
    }

    public int getFkServiceId() {
        return fkServiceId;
    }

    public void setFkServiceId(int fkServiceId) {
        this.fkServiceId = fkServiceId;
    }

    public Date getBildDetailDate() {
        return bildDetailDate;
    }

    public void setBildDetailDate(Date bildDetailDate) {
        this.bildDetailDate = bildDetailDate;
    }
    
    public int getFkProductId() {
        return fkProductId;
    }

    public void setFkProductId(int fkProductId) {
        this.fkProductId = fkProductId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billDetailId != null ? billDetailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillDetail)) {
            return false;
        }
        BillDetail other = (BillDetail) object;
        if ((this.billDetailId == null && other.billDetailId != null) || (this.billDetailId != null && !this.billDetailId.equals(other.billDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.BillDetail[ billDetailId=" + billDetailId + " ]";
    }
}

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dgmin
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByAptId", query = "SELECT a FROM Appointment a WHERE a.aptId = :aptId"),
    @NamedQuery(name = "Appointment.findByAptDate", query = "SELECT a FROM Appointment a WHERE a.aptDate = :aptDate"),
    @NamedQuery(name = "Appointment.findByFkUserId", query = "SELECT a FROM Appointment a WHERE a.fkUserId = :fkUserId"),
    @NamedQuery(name = "Appointment.findByAptStatus", query = "SELECT a FROM Appointment a WHERE a.aptStatus = :aptStatus"),
    @NamedQuery(name = "Appointment.findByAptServiceType", query = "SELECT a FROM Appointment a WHERE a.aptServiceType = :aptServiceType")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "APT_ID")
    private Integer aptId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "APT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date aptDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FK_USER_ID")
    private int fkUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "APT_STATUS")
    private String aptStatus;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "APT_SERVICE_TYPE")
    private String aptServiceType;

    public Appointment() {
    }

    public Appointment(Integer aptId) {
        this.aptId = aptId;
    }

    public Appointment(Integer aptId, Date aptDate, int fkUserId, String aptStatus, String aptServiceType) {
        this.aptId = aptId;
        this.aptDate = aptDate;
        this.fkUserId = fkUserId;
        this.aptStatus = aptStatus;
        this.aptServiceType = aptServiceType;
    }

    public Integer getAptId() {
        return aptId;
    }

    public void setAptId(Integer aptId) {
        this.aptId = aptId;
    }

    public Date getAptDate() {
        return aptDate;
    }

    public void setAptDate(Date aptDate) {
        this.aptDate = aptDate;
    }

    public int getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(int fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getAptStatus() {
        return aptStatus;
    }

    public void setAptStatus(String aptStatus) {
        this.aptStatus = aptStatus;
    }

    public String getAptServiceType() {
        return aptServiceType;
    }

    public void setAptServiceType(String aptServiceType) {
        this.aptServiceType = aptServiceType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aptId != null ? aptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.aptId == null && other.aptId != null) || (this.aptId != null && !this.aptId.equals(other.aptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.Appointment[ aptId=" + aptId + " ]";
    }
    
}

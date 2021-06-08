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
@Table(name = "worker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Worker.findAll", query = "SELECT w FROM Worker w"),
    @NamedQuery(name = "Worker.findByWkrId", query = "SELECT w FROM Worker w WHERE w.wkrId = :wkrId"),
    @NamedQuery(name = "Worker.findByWkrFirstName", query = "SELECT w FROM Worker w WHERE w.wkrFirstName = :wkrFirstName"),
    @NamedQuery(name = "Worker.findByWkrLastName", query = "SELECT w FROM Worker w WHERE w.wkrLastName = :wkrLastName"),
    @NamedQuery(name = "Worker.findByWkrTelNr", query = "SELECT w FROM Worker w WHERE w.wkrTelNr = :wkrTelNr"),
    @NamedQuery(name = "Worker.findByWrkEmail", query = "SELECT w FROM Worker w WHERE w.wrkEmail = :wrkEmail")})
public class Worker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WKR_ID")
    private Integer wkrId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "WKR_FIRST_NAME")
    private String wkrFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "WKR_LAST_NAME")
    private String wkrLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "WKR_TEL_NR")
    private String wkrTelNr;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "WRK_EMAIL")
    private String wrkEmail;

    public Worker() {
    }

    public Worker(Integer wkrId) {
        this.wkrId = wkrId;
    }

    public Worker(Integer wkrId, String wkrFirstName, String wkrLastName, String wkrTelNr, String wrkEmail) {
        this.wkrId = wkrId;
        this.wkrFirstName = wkrFirstName;
        this.wkrLastName = wkrLastName;
        this.wkrTelNr = wkrTelNr;
        this.wrkEmail = wrkEmail;
    }

    public Integer getWkrId() {
        return wkrId;
    }

    public void setWkrId(Integer wkrId) {
        this.wkrId = wkrId;
    }

    public String getWkrFirstName() {
        return wkrFirstName;
    }

    public void setWkrFirstName(String wkrFirstName) {
        this.wkrFirstName = wkrFirstName;
    }

    public String getWkrLastName() {
        return wkrLastName;
    }

    public void setWkrLastName(String wkrLastName) {
        this.wkrLastName = wkrLastName;
    }

    public String getWkrTelNr() {
        return wkrTelNr;
    }

    public void setWkrTelNr(String wkrTelNr) {
        this.wkrTelNr = wkrTelNr;
    }

    public String getWrkEmail() {
        return wrkEmail;
    }

    public void setWrkEmail(String wrkEmail) {
        this.wrkEmail = wrkEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wkrId != null ? wkrId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Worker)) {
            return false;
        }
        Worker other = (Worker) object;
        if ((this.wkrId == null && other.wkrId != null) || (this.wkrId != null && !this.wkrId.equals(other.wkrId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.ex.fh.model.Worker[ wkrId=" + wkrId + " ]";
    }
    
}

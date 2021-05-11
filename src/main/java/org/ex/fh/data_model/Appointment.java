/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.data_model;

import java.sql.Date;

/**
 *
 * @author dgmin
 */

enum AppointmentStatus {
    OPEN,
    CLOSED
}

public class Appointment {
    
    private int                 id;
    private Date                date;
    private AppointmentStatus   status;
    private ServiceType         serviceType;

    public Appointment(int id, Date date, AppointmentStatus status, ServiceType serviceType) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.serviceType = serviceType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }
    
}
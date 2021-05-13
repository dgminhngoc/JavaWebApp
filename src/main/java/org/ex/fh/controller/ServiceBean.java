/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.controller;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import org.ex.fh.model.Service;

/**
 *
 * @author dgmin
 */
@Named(value = "serviceBean")
@RequestScoped
public class ServiceBean {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    /**
     * Creates a new instance of ServiceBean
     */
    public ServiceBean() {
    }
    
    public List<Service> getListService() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Service> query = entityManager.createNamedQuery("Service.findAll", Service.class);
        
        return query.getResultList();
    }
}

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
import org.ex.fh.model.BillDetail;

/**
 *
 * @author dgmin
 */
@Named(value = "billDetailBean")
@RequestScoped
public class BillDetailBean {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    
    /**
     * Creates a new instance of BillDetailBean
     */
    public BillDetailBean() {
    }
    
    public List<BillDetail> getListService() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<BillDetail> query = entityManager.createNamedQuery("BillDetail.findAll", BillDetail.class);
        
        return query.getResultList();
    }
}

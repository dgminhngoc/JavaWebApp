/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ex.fh.data_model;

/**
 *
 * @author dgmin
 */
enum ServiceType {
    EYE_SERVICE,
    FOOT_SERVICE,
    HAIR_SERVICE,
    NAIL_SERVICE,
    FACE_SERVICE
}

public class Service {
    
    private int         id;
    private ServiceType type;
    private String      name;
    private String      description;
    private float       cost;

    public Service(int id, ServiceType type, String name, String description, float cost) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ServiceType getType() {
        return type;
    }

    public void setType(ServiceType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
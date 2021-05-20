/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Events {
    private int idEvent, idMember;
    private String nameEve, descriptionEve, photoEve;
    private Date dateEve;

    public Events() {
    }

    public Events(int idEvent, int idMember, String nameEve, String descriptionEve, String photoEve, Date dateEve) {
        this.idEvent = idEvent;
        this.idMember = idMember;
        this.nameEve = nameEve;
        this.descriptionEve = descriptionEve;
        this.photoEve = photoEve;
        this.dateEve = dateEve;
    }

    public Events(int idMember, String nameEve, String descriptionEve, String photoEve, Date dateEve) {
        this.idMember = idMember;
        this.nameEve = nameEve;
        this.descriptionEve = descriptionEve;
        this.photoEve = photoEve;
        this.dateEve = dateEve;
    }

    public Events(String toString, String toString0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getNameEve() {
        return nameEve;
    }

    public void setNameEve(String nameEve) {
        this.nameEve = nameEve;
    }

    public String getDescriptionEve() {
        return descriptionEve;
    }

    public void setDescriptionEve(String descriptionEve) {
        this.descriptionEve = descriptionEve;
    }

    public String getPhotoEve() {
        return photoEve;
    }

    public void setPhotoEve(String photoEve) {
        this.photoEve = photoEve;
    }

    public Date getDateEve() {
        return dateEve;
    }

    public void setDateEve(Date dateEve) {
        this.dateEve = dateEve;
    }
    
}

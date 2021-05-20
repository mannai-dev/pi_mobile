/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Reclamation {
    
     int id;
     String title,description,answer;
     Date date;
     String archive;
     int id_user;

    public Reclamation() {
    }

    public Reclamation(int id, String title, String description, String answer, Date date, String archive, int id_user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.answer = answer;
        this.date = date;
        this.archive = archive;
        this.id_user = id_user;
    }

    public Reclamation(String title, String description, String answer, Date date, String archive) {
        this.title = title;
        this.description = description;
        this.answer = answer;
        this.date = date;
        this.archive = archive;
    }

    public Reclamation(String title, String description, String answer, Date date, String archive, int id_user) {
        this.title = title;
        this.description = description;
        this.answer = answer;
        this.date = date;
        this.archive = archive;
        this.id_user = id_user;
    }

    public Reclamation(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Reclamation(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Reclamation(String title, String description, String answer, Date date) {
        this.title = title;
        this.description = description;
        this.answer = answer;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
     
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author mortadha
 */

public class User {

    private int id_user;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String adresse;
    private int telephone;
    private String email;
    private String photo;
    private String role;



    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getTelephone() {
        return telephone;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User() {
    }

    public User(int id_user, String username, String password, String firstName, String lastName, String adresse, int telephone, String email, String photo, String role) {
        this.id_user = id_user;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.photo = photo;
        this.role = role;
    }

    public User(String username, String password, String firstName, String lastName, String adresse, int telephone, String email, String photo, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.photo = photo;
        this.role = role;
    }

  

    @Override
    public String toString() {
        return "User{" + "idUser=" + id_user + ", nom=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", adresse =" + adresse + ", telephone=" + telephone + ", email=" + email + ", photo=" + photo + ", role=" + role +'}';
    }

    
    

    


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}

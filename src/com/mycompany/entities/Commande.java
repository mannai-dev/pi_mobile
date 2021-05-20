/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author moham
 */
public class Commande {
    private int id_commande ;
     private String id_commande2 ;
    private String user_name;
    private String vendor_name;
    private String adresse_livraison;
    private int total;
     private String total2;
    private int id_user;
    private String id_user2;
    

    public Commande(String text, int parseInt) {
       this.adresse_livraison = adresse_livraison;
        this.total = total;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Commande(int id_commande, String adresse_livraison, int total) {
        this.id_commande = id_commande;
        this.adresse_livraison = adresse_livraison;
        this.total = total;
    }
     public Commande( String adresse_livraison, int id_user,int total) {
        
        this.adresse_livraison = adresse_livraison;
        this.id_user = id_user;
        this.total = total;
    }

   
    public Commande() {
    }
//
//    public Commande(users user_name, Vendor vendor_name, String adresse_livraison, int total) {
//        this.user_name = user_name;
//        this.vendor_name = vendor_name;
//        this.adresse_livraison = adresse_livraison;
//        this.total = total;
//    }
//
//    public Commande(int id_commande, users user_name, Vendor vendor_name, String adresse_livraison, int total) {
//        this.id_commande = id_commande;
//        this.user_name = user_name;
//        this.vendor_name = vendor_name;
//        this.adresse_livraison = adresse_livraison;
//        this.total = total;
//    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

//    public users getUser_name() {
//        return user_name;
//    }
//
//    public void setUser_name(users user_name) {
//        this.user_name = user_name;
//    }
//
//    public Vendor getVendor_name() {
//        return vendor_name;
//    }
//
//    public void setVendor_name(Vendor vendor_name) {
//        this.vendor_name = vendor_name;
//    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getId_commande2() {
        return id_commande2;
    }

    public void setId_commande2(String id_commande2) {
        this.id_commande2 = id_commande2;
    }

    public String getTotal2() {
        return total2;
    }

    public void setTotal2(String total2) {
        this.total2 = total2;
    }

    public String getId_user2() {
        return id_user2;
    }

    public void setId_user2(String id_user2) {
        this.id_user2 = id_user2;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }
    
    

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", user_name=" +  ", adresse_livraison=" + adresse_livraison + ", total=" + total + '}';
    }
    
    
    
}

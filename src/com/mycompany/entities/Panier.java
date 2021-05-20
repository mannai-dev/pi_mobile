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
public class Panier {
    private int id_panier;
   private Produits produit;
   // private users user;
    private int quantity;
    private int total;
    private int id_product;
    private int id_user;
    private String title;
    private int price;
    private int stock;
    private String photo_prod;
    private String description_prod;

    public Panier(int id_panier, int quantity, int total, int id_product, String title, int price,int stock) {
        this.id_panier = id_panier;
        this.quantity = quantity;
        this.total = total;
        this.id_product = id_product;
         this.title = title;
        this.price = price;
        this.stock = stock;
        
    }

    public Panier(int id_panier, int id_product, int id_user, int quantity, int total) {
        this.id_panier = id_panier;
        this.id_product = id_product;
        this.id_user = id_user;
        this.quantity = quantity;
        this.total = total;
        
    }
    
    public Panier( int quantity, int total,  String title, int price,int stock) {
       
        this.quantity = quantity;
        this.total = total;
        
        this.title = title;
        this.price = price;
        this.stock = stock;
        
    }

    public Panier(int id_panier,int id_product, int quantity, int total, String title) {
        this.id_panier = id_panier;
        this.id_product= id_product;
        this.quantity = quantity;
        this.total = total;
        this.title = title;
    }
    

  
  

    
    public Panier(int id_panier, Produits produit, int quantity, int total, int id_product, int id_user, String title, int price) {
        this.id_panier = id_panier;
        this.produit = produit;
      //  this.user = user;
        this.quantity = quantity;
        this.total = total;
        this.id_product = id_product;
        this.id_user = id_user;
        this.title = title;
        this.price = price;
    }
    
    
    public Panier() {
    }
    

    public Panier( int id_user, int id_product, int quantity,int total) {
         this.id_user = id_user;
        
        this.id_product = id_product;
        this.quantity = quantity;
        this.total = total;
    }

    public Panier(int id_panier,int id_product,int id_user, int quantity, int total, String photo_prod,  String title, int price, int stock, String description_prod) {
        this.id_panier = id_panier;
        this.id_product = id_product;
        this.id_user= id_user;
        this.quantity = quantity;
        this.total = total;
        this.photo_prod = photo_prod;
        this.title = title;
        this.price = price;
        this.stock = stock;
        this.description_prod= description_prod;
        
    }

    public String getDescription_prod() {
        return description_prod;
    }

    public void setDescription_prod(String description_prod) {
        this.description_prod = description_prod;
    }

    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto_prod() {
        return photo_prod;
    }

    public void setPhoto_prod(String photo_prod) {
        this.photo_prod = photo_prod;
    }

   

  
    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

   

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public Produits getProduit() {
        return produit;
    }

    public void setProduit(Produits produit) {
        this.produit = produit;
    }

//    public users getUser() {
//        return user;
//    }
//
//    public void setUser(users user) {
//        this.user = user;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    @Override
    public String toString() {
        return "Panier{" + "id_panier=" + id_panier + ", produit=" + produit +  ", quantity=" + quantity + ", total=" + total + ", id_product=" + id_product + ", id_user=" + id_user + ", title=" + title + ", price=" + price + ", stock=" + stock + '}';
    }
    
    

   

      
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author LeNoVo
 */
public class Produits {
    int idProduct,stock,idVendor,price;
    String photoProd,descriptionProd,title;

    public Produits() {
    }

    public Produits(int idProduct, int stock, int idVendor, int price, String photoProd, String descriptionProd, String title) {
        this.idProduct = idProduct;
        this.stock = stock;
        this.idVendor = idVendor;
        this.price = price;
        this.photoProd = photoProd;
        this.descriptionProd = descriptionProd;
        this.title = title;
    }

    public Produits(int stock, int idVendor, int price, String photoProd, String descriptionProd, String title) {
        this.stock = stock;
        this.idVendor = idVendor;
        this.price = price;
        this.photoProd = photoProd;
        this.descriptionProd = descriptionProd;
        this.title = title;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getIdVendor() {
        return idVendor;
    }

    public void setIdVendor(int idVendor) {
        this.idVendor = idVendor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhotoProd() {
        return photoProd;
    }

    public void setPhotoProd(String photoProd) {
        this.photoProd = photoProd;
    }

    public String getDescriptionProd() {
        return descriptionProd;
    }

    public void setDescriptionProd(String descriptionProd) {
        this.descriptionProd = descriptionProd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
}

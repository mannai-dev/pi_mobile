/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.utils.Statics;
import entities.Produits;
import java.util.ArrayList;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

/**
 *
 * @author LeNoVo
 */
public class ServicesProduits {
    
    
    
    public static ServicesProduits instance = null ;
    private ConnectionRequest req;
    
   public static ServicesProduits getInstance(){
       if(instance == null)
           instance = new ServicesProduits();
          return instance;
          
   
   }
    
    public ServicesProduits() {
        req = new ConnectionRequest();
        
        
         
        
        
    }
    public void ajouterProduits(Produits produits){
        String url = Statics.BASE_URL +"/addprod?descriptionProd="+produits.getDescriptionProd()+"&price="+produits.getPrice()+"&photoProd="+produits.getPhotoProd()+"&stock="+produits.getStock()+"&idVendor="+produits.getIdVendor()+"&title="+produits.getTitle();
         req.setUrl(url);
         System.out.println(url);
        req.addResponseListener((e) -> {
        
        
        String str = new String(req.getResponseData());
        
        System.out.println("data == "+str);
        
        });
        NetworkManager.getInstance().addToQueueAndWait(req);  
    }
    
    
    
    
    
    
    public ArrayList<Produits>affichageproduits() {
        ArrayList<Produits> result = new ArrayList<>();
        
        String url = Statics.BASE_URL + "/displayprod";
        
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{ 
                    Map<String,Object>mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapProduits.get("root");
                    for(Map<String,Object> obj : listOfMaps) {
                        Produits p =new Produits();
                        
                        float idProduct = Float.parseFloat(obj.get("idProduct").toString());
                        float stock = Float.parseFloat(obj.get("stock").toString());
                        float idVendor = Float.parseFloat(obj.get("idVendor").toString());
                        float price = Float.parseFloat(obj.get("price").toString());
                        String title = obj.get("title").toString();
                        String descriptionProd = obj.get("descriptionProd").toString();
                        String photoProd = obj.get("photoProd").toString();
                        
                        p.setIdProduct((int)idProduct);
                        p.setStock((int)stock);
                        p.setDescriptionProd(descriptionProd);
                        p.setPhotoProd(photoProd);
                        p.setPrice((int)price);
                        p.setTitle(title);
                        p.setIdVendor((int)idVendor);
                        
                        result.add(p);
                        
                         
                        
                        
                        
                        
                       
                    }
                }catch(Exception ex) {
                    ex.printStackTrace();
                }
                
            }
            
            
            
        }
        
        
        
        );
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return result;
        
    }
   
    
}

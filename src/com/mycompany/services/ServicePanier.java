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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commande;
import com.mycompany.entities.Panier;
import com.mycompany.utils.Statics;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author moham
 */
public class ServicePanier {

    public int tot=0;
    public ArrayList<Panier> paniers;

    public static ServicePanier instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    public ServicePanier() {
        req = new ConnectionRequest();
    }

    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    
    
      public ArrayList<Panier> CartList() {
        ArrayList<Panier> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/panier/affichemobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapPaniers = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapPaniers.get("root");
                    for (Map<String, Object> obj : listOfMaps) {

                        Panier t = new Panier();
                        //{"idPanier":19,"idProduct":2,"idUser":2,"quantity":7,"total":2800}
                        float idPanier = Float.parseFloat(obj.get("idPanier").toString());
                        float idProduct = Float.parseFloat(obj.get("idProduct").toString());
                        float idUser = Float.parseFloat(obj.get("idUser").toString());
                        float quantity = Float.parseFloat(obj.get("quantity").toString());
                        float total = Float.parseFloat(obj.get("total").toString());

                        t.setId_panier((int)idPanier);
                        t.setId_product((int)idProduct);
                        t.setId_user((int) idUser);
                        t.setQuantity((int)quantity);
                        t.setTotal((int)total);
                        
                        
                        result.add(t);
                        tot=tot+t.getTotal();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        //Aprés l'exécution de l'URL on attend la réponse du serveur 
        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }
    
      
      
    public boolean deleteProd(int id) {
        String url = Statics.BASE_URL + "/panier/deletemobile/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        //Aprés l'exécution de l'URL on attend la réponse du serveur 
        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }
}

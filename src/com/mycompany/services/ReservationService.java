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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import models.Reservation;
import com.mycompany.utils.Statics;

/**
 *
 * @author administrater
 */
public class ReservationService {
      //var
    boolean resultOK;
    ConnectionRequest req;
    static ReservationService instance;
    ArrayList<Reservation> reservations = new ArrayList<>();
    
    
    //constructor
    public ReservationService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static ReservationService getInstance(){
        
        if (instance == null) {
            instance = new ReservationService();
        }
        
        return instance;
    }
    
    
    
    
    //ADD RESERVATIONS 
    public boolean addReservationAction(Reservation r ){
        
        String url = Statics.BASE_URL + "/jsonreservations/addReservationsJSON/new?"+"idPlanning="+ r.getId_planning()+ "&idMember=" + r.getId_member()+ "&idCoach=" +r.getId_coach()+ "&email=" +r.getEmail();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
          public boolean deleteItemReservation(int idReservation) {
        String url = Statics.BASE_URL + "/jsonreservations/deleteReservationsJSON/"+idReservation;
                 //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
    
    //PARSE RESERVATIONS JSON : convert JSON to java objects
    public ArrayList<Reservation> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> reservationsListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> reservationsList = (ArrayList<Map<String,Object>>) reservationsListJson.get("root");
            
            for (Map<String, Object> obj : reservationsList) {

                Reservation r = new Reservation();
                float idReservation = Float.parseFloat(obj.get("idReservation").toString());
                r.setId_reservation((int) idReservation);
                float idPlanning = Float.parseFloat(obj.get("idPlanning").toString());
                r.setId_planning((int) idPlanning);
                float idMember = Float.parseFloat(obj.get("idMember").toString());
                r.setId_member((int) idMember);
                float idCoach = Float.parseFloat(obj.get("idCoach").toString());
                r.setId_coach((int) idCoach);
                r.setEmail(obj.get("email").toString());
              

                reservations.add(r);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return reservations;  
    }



    //GET RESERVATIONS
    public ArrayList<Reservation> getReservations(){
        
         String url = Statics.BASE_URL+"/jsonreservations/Allreservations";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             reservations = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return reservations;
    }
    
}

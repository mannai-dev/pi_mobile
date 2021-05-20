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
import models.Planning;
import com.mycompany.utils.Statics;

/**
 *
 * @author administrater
 */
public class PlanningService {
       //var
    boolean resultOK;
    ConnectionRequest req;
    static PlanningService instance;
    ArrayList<Planning> plannings = new ArrayList<>();
    
    
    //constructor
    public PlanningService() {
        req = new ConnectionRequest();
    }
    
    //SINGLETON
    public static PlanningService getInstance(){
        
        if (instance == null) {
            instance = new PlanningService();
        }
        
        return instance;
    }
    
    
    
    
    //ADD PLANNINGS 
    public boolean addPlanningAction(Planning p ){
        
        String url = Statics.BASE_URL + "/jsonplannig/addPlanningJSON/new?"+"idCoach="+ p.getId_coach ()+ "&coachName=" + p.getCoach_name()+ "&course=" +p.getCourse()+ "&startlesson=" +p.getStartLesson()+ "&endlesson=" +p.getEndLesson()+ "&maxnbr=" +p.getMaxNbr()+ "&nbractuel=" +p.getNbractuel();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
       public boolean deleteItem(int idPlanning) {
        String url = Statics.BASE_URL + "/jsonplannig/deletePlanningJSON/"+idPlanning;
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
    
    
    //PARSE PLANNINGS JSON : convert JSON to java objects
    public ArrayList<Planning> parseJSONAction(String textJson){
        
        JSONParser j = new JSONParser();
        
        try {
            
            Map<String, Object> planningsListJson = j.parseJSON(new CharArrayReader(textJson.toCharArray()));
            ArrayList<Map<String,Object>> planningsList = (ArrayList<Map<String,Object>>) planningsListJson.get("root");
            
            for (Map<String, Object> obj : planningsList) {

                Planning p = new Planning();
                float id = Float.parseFloat(obj.get("idPlanning").toString());
                p.setId_planning((int) id);
                float id_coach = Float.parseFloat(obj.get("idCoach").toString());
                p.setId_coach((int) id_coach);
                p.setCoach_name(obj.get("coachName").toString());
                p.setCourse(obj.get("course").toString());
                p.setStartLesson(obj.get("startlesson").toString());
                p.setEndLesson(obj.get("endlesson").toString());
                float max = Float.parseFloat(obj.get("maxnbr").toString());
                p.setMaxNbr((int) max);
                float nbrActuel = Float.parseFloat(obj.get("nbractuel").toString());
                p.setNbractuel((int) nbrActuel);

                plannings.add(p);

            }
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return plannings;  
    }



    //GET PLANNINGS
    public ArrayList<Planning> getPlannings(){
        
         String url = Statics.BASE_URL+"/jsonplannig/Allplanning";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
             plannings = parseJSONAction(new String(request.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return plannings;
    }
    
}

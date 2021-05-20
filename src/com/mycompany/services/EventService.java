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
import com.codename1.ui.List;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Events;
import com.mycompany.utils.Statics;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Administrator
 */
public class EventService extends Form {
    public static EventService instance = null;
    public static boolean resultOK = true;
    private ConnectionRequest req;
 public String json;
    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public EventService() {
        req = new ConnectionRequest();
    }
public ArrayList <Events> Display (){
      ArrayList<Events> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/displayEvents";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                JSONParser json;
                json = new JSONParser();

                try {
                    Map<String, Object> mapEvents = json.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    java.util.List<Map<String, Object>> listofMaps = (java.util.List<Map<String, Object>>) mapEvents.get("root");

                    for (Map<String, Object> obj : listofMaps) {
                        Events re = new Events();
                       float id = Float.parseFloat(obj.get("idEvent").toString());

                        String name = obj.get("nameEve").toString();
                        String description = obj.get("descriptionEve").toString();
//                       String photo = obj.get("photo").toString();
                        
                        
                        

                       re.setIdEvent((int)id);

                        re.setNameEve(name);
                        re.setDescriptionEve(description);
//                     re.setPhotoEve(photo);
                        
                       
                        
                        result.add(re);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    
   }
   public String addeve(TextField descriptionEve,TextArea nameEve,String photo){
       String url = Statics.BASE_URL + "/addEvent?descriptionEve=" + descriptionEve.getText() + "&nameEve="+nameEve.getText()+"&photo="+photo;
       req.setUrl(url);

         req.addResponseListener((evt) -> {

            JSONParser j = new JSONParser();
            json = new String(req.getResponseData()) + "";
             sendMail();

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return json;
   }
       public boolean deleteReclamation(int id){
        
        Events re = new Events();
        String url = Statics.BASE_URL + "/deleteEvent?idEvent=" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    
    }
         public void PrintEvent(){
        
        Events re = new Events();
        String url = Statics.BASE_URL + "/events/pdfevents" ;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
     
    
    }
          //Send MAIL
    public void sendMail() {

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "stpm.gmail.com");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("New event <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, "mohamed.mannai3@esprit.tn");
            msg.setSubject("New Event");
            msg.setSentDate(new Date(System.currentTimeMillis()));
           // String mp = UserService.getInstance().getPasswordByEmail(email.getText().toString(), res);
            String txt = "Event added   " + "Cordialement.";
            msg.setText(txt);
            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "workit.noreplay2021@gmail.com", "workit.noreplay2021@gmail.com w");
            st.sendMessage(msg, msg.getAllRecipients());

            System.out.println("server response : " + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
  
         
}

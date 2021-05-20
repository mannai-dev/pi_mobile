/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import models.Reservation;
import utils.Statics;
import gui.AddReservationForm;

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
        
        String url = Statics.BASE_URL + "/addReservationsJSON/new?"+"idPlanning="+ r.getId_planning()+ "&idMember=" + r.getId_member()+ "&idCoach=" +r.getId_coach()+ "&email=" +r.getEmail();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((evt) -> {
            resultOK = req.getResponseCode()==200;
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
//            sendEmail();
        
   
    }
          public boolean deleteItemReservation(int idReservation) {
        String url = Statics.BASE_URL + "/deleteReservationsJSON/"+idReservation;
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
        
         String url = Statics.BASE_URL+"/Allreservations";
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
//       public void sendEmail(){
//     try{
//            String host ="smtp.gmail.com" ;
//            String user = "workit.noreplay2021@gmail.com";
//            String pass = "workit.noreplay2021@gmail.com w";
//            String to = .getText();
//            String from = "workit.noreplay2021@gmail.com";
//            String subject = "Work-It - Reservation Done";
//            boolean sessionDebug = false;
//
//            Properties props = System.getProperties();
//
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.host", host);
//            props.put("mail.smtp.port", "587");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.required", "true");
//
//            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//            Session mailSession = Session.getDefaultInstance(props, null);
//            mailSession.setDebug(sessionDebug);
//            Message msg = new MimeMessage(mailSession);
//            msg.setFrom(new InternetAddress(from));
//            InternetAddress[] address = {new InternetAddress(to)};
//           
//            msg.setRecipients(Message.RecipientType.TO, address);
//            msg.setSubject(subject); msg.setSentDate(new Date());
//                
//            
//            MimeBodyPart messageBodyPart = new MimeBodyPart();
//            Multipart multipart = new MimeMultipart();
//        
//     
//            messageBodyPart = new MimeBodyPart();   
//    
//            messageBodyPart.setText("Goodmorning Mme/Mr "+" "+", Your reservation is done ");
//            multipart.addBodyPart(messageBodyPart);
//    
//        
//            msg.setContent(multipart);
//
//       
//           javax.mail.Transport transport=mailSession.getTransport("smtp");
//           transport.connect(host, user, pass);
//           transport.sendMessage(msg, msg.getAllRecipients());
//           transport.close();
//                   System.out.println("Mail send");
//
//        }catch(Exception ex)
//        {
//            System.out.println(ex);
//        }
//}
}

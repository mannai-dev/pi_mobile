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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Commande;
import com.mycompany.utils.Statics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author moham
 */
public class ServiceCommande {

    public ArrayList<Commande> commandes;

    public static ServiceCommande instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    public ServiceCommande() {
        req = new ConnectionRequest();
    }

    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }

    public void addCom(TextField email, TextField adresse, TextField total, Resources res) {

        String url = Statics.BASE_URL + "/commande/newmobile?adresse=" + adresse.getText().toString() + "&total=" + total.getText().toString();

        req.setUrl(url);

        // Controle de saisie
        if (email.getText().equals("") && adresse.getText().equals("") && total.getText().equals("")) {
            Dialog.show("Error", "Missing information", "OK", null);

        } else if (email.getText().equals("")) {
            Dialog.show("Error", "Add your email", "OK", null);

        } else if (adresse.getText().equals("")) {
            Dialog.show("Error", "Add your adres", "OK", null);

        } else if (total.getText().equals("")) {
            Dialog.show("Error", "Total missing", "OK", null);
        } else {
            sendMail(email, res);
            Dialog.show("Success", "Order passed", "OK", null);
        }

//        if (adresse.getText().equals(" ") && total.getText().equals(" ")) {
//
//            Dialog.show("ERREUR", "Veuillez remplir les champs", "OK", null);
//        }
        //Execution de l'URL 
        req.addResponseListener((e) -> {

            byte[] data = (byte[]) e.getMetaData();
            String responsableData = new String(data);

            System.out.println("data =>" + responsableData);
        }
        );
        //Aprés l'exécution de l'URL on attend la réponse du serveur 
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Commande> parseCommandes(String jsonText) {
        try {
            commandes = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> commandesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) commandesListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                //{"idCommande":1,"vendorName":"mourad","adresseLivraison":"mourouj","total":75,"idUser":2,"idVendor":1}               
                Commande t = new Commande();
                String id_commande = (String) obj.get("idCommande");
                String vName = (String) obj.get("vendorName");
                String adres = (String) obj.get("adresseLivraison");
                String total = (String) obj.get("total");
                String idU = (String) obj.get("idUser");
                String idV = (String) obj.get("idVendor");

                t.setId_commande2(id_commande);
                t.setAdresse_livraison(adres);
                t.setTotal2(total);
                t.setId_user2(idU);
                //Ajouter la tâche extraite de la réponse Json à la liste
                commandes.add(t);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return commandes;
    }

    public ArrayList<Commande> getAllCommandes() {
        String url = Statics.BASE_URL + "/commande/affichemobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
//                commandes = parseCommandes(new String(req.getResponseData()));
              //  commandes = OrderList(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return commandes;
    }

    public boolean deleteOrder(int id) {
        String url = Statics.BASE_URL + "/commande/deletemobile/" + id;

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
    //List

   // public ArrayList<Commande> OrderList(String jsonText) {
        public ArrayList<Commande> OrderList() {
        ArrayList<Commande> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/commande/affichemobile";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapCommandes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapCommandes.get("root");
                    for (Map<String, Object> obj : listOfMaps) {

                        Commande t = new Commande();
                        //int id_commande = Integer.parseInt(obj.get("idCommande").toString());
                        float id_commande = Float.parseFloat(obj.get("idCommande").toString());
                        String vName = obj.get("vendorName").toString();
                        String adres = obj.get("adresseLivraison").toString();
                        float total = Float.parseFloat(obj.get("total").toString());
                        float idU = Float.parseFloat(obj.get("idUser").toString());

                        t.setId_commande((int)id_commande);
                        t.setAdresse_livraison(adres);
                        t.setTotal((int)total);
                        t.setId_user((int)idU);
                        t.setVendor_name(vName);
                        result.add(t);
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

     public ArrayList<Commande> getOrder(){
        
         String url = Statics.BASE_URL+"/commande/affichemobile";
         ConnectionRequest request = new ConnectionRequest(url);
         request.setPost(false);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                 
          //   commandes = OrderList(new String(req.getResponseData()));
             request.removeResponseListener((ActionListener<NetworkEvent>) this);
             }
         });
         
        
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return commandes;
    }
    
    //Send MAIL
    public void sendMail(TextField email, Resources res) {

        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "stpm.gmail.com");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getInstance(props, null);
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("New order <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
            msg.setSubject("New Order");
            msg.setSentDate(new Date(System.currentTimeMillis()));
           // String mp = UserService.getInstance().getPasswordByEmail(email.getText().toString(), res);
            String txt = "Commande passée avec succée   " + "Cordialement.";
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

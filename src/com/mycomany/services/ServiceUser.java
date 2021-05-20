/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.entities.User;
import com.mycomany.utils.Statics;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author mortadha
 */
public class ServiceUser {
    
 public static ServiceUser instance = null;
    private ConnectionRequest req;
    public ArrayList<User> users;
    public boolean resultOK;
    public User user;

    private ServiceUser() {
        req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ArrayList<User> parseTasks(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> entreprisesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map <String, Object>> list = (List<Map<String, Object>>) entreprisesListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map <String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                User u = new User();
                //System.out.println(u);
                float id_user = Float.parseFloat(obj.get("idUser").toString());
                u.setId_user((int) id_user);
                //System.out.println(u);
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                u.setFirstName(obj.get("firstName").toString());
                u.setLastName(obj.get("lastName").toString());
                u.setAdresse(obj.get("adresse").toString());
                float tel = Float.parseFloat(obj.get("telephone").toString());
                u.setTelephone((int) tel);
                u.setEmail(obj.get("email").toString());
                u.setPhoto(obj.get("photo").toString());
                u.setRole(obj.get("role").toString());

                 // System.out.println(u);
                //Ajouter la tâche extraite de la réponse Json à la liste
                users.add(u);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return users;
    }

    public User verifer(String username, String password) {
        // Users user = null;
        String url = Statics.BASE_URL +"/user/m/verifier?username=" + username + "&password=" + password;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // System.out.println(new String(req.getResponseData()));
                //System.out.println(req.getResponseData());
                String res = new String(req.getResponseData());

                if (res.equalsIgnoreCase("null")) {
                    user = null;
                } else {
                    users = parseTasks(new String(req.getResponseData()));
                    user = users.get(0);
                }
                //  System.out.println(users);
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }

    public User veriferemail(String email) {
        // Users user = null;
        String url = Statics.BASE_URL + "/user/m/verifier/email?email=" + email;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // System.out.println(new String(req.getResponseData()));
                //System.out.println(req.getResponseData());
                String res = new String(req.getResponseData());

                if (res.equalsIgnoreCase("null")) {
                    user = null;
                } else {
                    users = parseTasks(new String(req.getResponseData()));
                    user = users.get(0);
                }
                //  System.out.println(users);
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }

    public void sendEmail(String email, int code) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.auth", "true");

            Session session = Session.getInstance(props, null);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("reinitialisation mot de passe  <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("[Work-IT] reinitialisation mot de passe ");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            String txt = "reinitialisation mot de passe : code de verification : " + code;
            msg.setText(txt);

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "work.itpidev@gmail.com", "Workit2021");

            st.sendMessage(msg, msg.getAllRecipients());
            System.out.println("server response :" + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User resetpassword(User Resetuser) {
        // Users user = null;
        String url = Statics.BASE_URL + "/user/m/resetpassword?id=" + Resetuser.getId_user()+"&password="+Resetuser.getPassword() ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                // System.out.println(new String(req.getResponseData()));
                //System.out.println(req.getResponseData());
                String res = new String(req.getResponseData());

                if (res.equalsIgnoreCase("null")) {
                    user = null;
                } else {
                    users = parseTasks(new String(req.getResponseData()));
                    user = users.get(0);
                }
                //  System.out.println(users);
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;

    }
    public boolean addUser(User u) {

        String url = Statics.BASE_URL + "/user/m/adduser?username=" + u.getUsername()
                + "&password=" + u.getPassword() + "&firstName=" +u.getFirstName()
                + "&lastName=" + u.getLastName() + "&adresse=" + u.getAdresse() + "&telephone=" + u.getTelephone()
                + "&photo=" + u.getPhoto() + "&email=" + u.getEmail() + "&role=" +u.getRole() +"";

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    /**
     *
     * @param email
     * @param code
     * @return 
     */
    public void sendEmail2(String email, int code) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtps.host", "smtp.gmail.com");
            props.put("mail.smtps.auth", "true");

            Session session = Session.getInstance(props, null);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("Creation Compte  <monEmail@domaine.com>"));
            msg.setRecipients(Message.RecipientType.TO, email);
            msg.setSubject("[Work-IT] Creation Compte ");
            msg.setSentDate(new Date(System.currentTimeMillis()));

            String txt = "Creation Compte : code de verification : " + code;
            msg.setText(txt);

            SMTPTransport st = (SMTPTransport) session.getTransport("smtps");
            st.connect("smtp.gmail.com", 465, "work.itpidev@gmail.com", "Workit2021");

            st.sendMessage(msg, msg.getAllRecipients());
            System.out.println("server response :" + st.getLastServerResponse());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<User> getallusers() {
        String url = Statics.BASE_URL + "/user/m/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
    
    

}


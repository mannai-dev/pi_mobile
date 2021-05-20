/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Events;
import com.mycompany.services.EventService;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public class EventsFrom extends BaseForm {

    public EventsFrom(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);

        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        super.addSideMenu(res);

        Button afficher = new Button("Afficher");
        Button ajouter = new Button("Add");
      //  Button delete = new Button("Delete");
        Events u = new Events();
        TextField a = new TextField();
//        ArrayList<Events> list = EventService.getInstance().Display();
//        for (Events e : list) {
//
////            Label label = new Label(""+e.getNameEve());
////            add(label);
//            a.setText(e.getNameEve());
//        }
  ArrayList<Events>list = EventService.getInstance().Display();
   //     System.out.print(list.get(1));
        for(Events rec : list){
            
            String urlImage = "smily.png";
            
            Image placeHolder=Image.createImage(120,90);
            EncodedImage enc=EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim=URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
        
       // addButton(urlim,rec,res);
        
        ScaleImageLabel image=new ScaleImageLabel(urlim);
        
        Container containerImg=new Container();
        
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }

        Container content = BoxLayout.encloseY(
                new Label("Modifier", "LogoLabel"),
                new FloatingHint(a), afficher, ajouter);
        add(BorderLayout.CENTER, content);
        content.setScrollableY(true);

        ajouter.addActionListener((evt) -> {
            new AddEvent(res).show();
        });
        afficher.addActionListener((evt) -> {
            new ListEventForm(res).show();
        });

    }
           // Button pdf = new Button("Print");
           


}

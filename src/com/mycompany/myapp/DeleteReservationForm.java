/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.mycompany.services.ReservationService;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author administrater
 */
public class DeleteReservationForm extends BaseForm{
      
    public DeleteReservationForm(Resources res) {
         super.addSideMenu(res);
        setTitle("Delete reservation");
        setLayout(BoxLayout.y());
        TextField idReservation = new TextField("","reservation id");
         Button btnValide = new Button("Delete reservation");
        addAll(idReservation,btnValide);
        btnValide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ReservationService ReservationsService=new ReservationService();
                ReservationsService.deleteItemReservation(Integer.parseInt(idReservation.getText()));
            }
        });
           
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());    }

 
   
}

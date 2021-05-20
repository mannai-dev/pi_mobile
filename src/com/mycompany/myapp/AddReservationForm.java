/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.mycompany.services.ReservationService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import models.Reservation;

/**
 *
 * @author administrater
 */

    public class AddReservationForm extends BaseForm{
    
    public AddReservationForm(Resources res){
         super.addSideMenu(res);
        this.setTitle("Add Reservation");
        this.setLayout(BoxLayout.y());
        
        TextField tfidPlanning = new TextField("", "Insert planning id");
        TextField tfidMember = new TextField("", "Insert member name ");
        TextField tfidCoach = new TextField("", "Insert coach id");
        TextField tfemail = new TextField("", "Insert email ");
       
        
        
        Button submitReservationBtn = new Button("Submit");
        submitReservationBtn.addActionListener((evt) -> {
            
            if (tfidCoach.getText().length() ==0 || tfidPlanning.getText().length()==0 || tfidMember.getText().length()==0 || tfidCoach.getText().length()==0 || tfemail.getText().length()==0 ) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                    Reservation reservation = new Reservation(Integer.parseInt(tfidPlanning.getText()),Integer.parseInt(tfidMember.getText()),Integer.parseInt(tfidCoach.getText()),tfemail.getText());
                    if (ReservationService.getInstance().addReservationAction(reservation)) {
                        Dialog.show("Success", "reservation added successfully.",null, "OK");
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "reservation's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        this.addAll(tfidPlanning,tfidMember,tfidCoach,tfemail, submitReservationBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
        
    }
    
}



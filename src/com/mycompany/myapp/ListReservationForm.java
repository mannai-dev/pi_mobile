/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.mycompany.services.PlanningService;
import com.mycompany.services.ReservationService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import javax.mail.Quota;

/**
 *
 * @author administrater
 */
public class ListReservationForm extends BaseForm{

    public ListReservationForm(Resources res){
        super.addSideMenu(res);
        this.setTitle("Reservations List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel eservationsListSP = new SpanLabel();
        eservationsListSP.setText(ReservationService.getInstance().getReservations().toString());
        
        this.add(eservationsListSP);
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        Button btn1 = new Button("add");
        Button btn2 = new Button("delete");
        
         btn1.addActionListener((e) -> {
            new AddReservationForm(res).show();

        });
         
        btn2.addActionListener((e) -> {
            new DeleteReservationForm(res).show();

        });
  Container content = BoxLayout.encloseY(
                new Label("Order", "LogoLabel"),
                
                createLineSeparator(),
                btn1,btn2
               
        );
   this.add(content);
    }

    
}

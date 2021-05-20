/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Services.PlanningService;
import Services.ReservationService;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author administrater
 */
public class ListReservationForm extends Form{
 
    public ListReservationForm(){
        
        this.setTitle("Reservations List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel eservationsListSP = new SpanLabel();
        eservationsListSP.setText(ReservationService.getInstance().getReservations().toString());
        
        this.add(eservationsListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
    }

    
}

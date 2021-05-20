/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author administrater
 */
 public class HomeForm extends Form{
    
    
    public HomeForm(){
        
        this.setTitle("Home Form");
        this.setLayout(BoxLayout.y());
        
        Button addPlanningBtn = new Button("Add Planning");
        Button listPlanningBtn = new Button("Planning List");
        Button addReservartionBtn = new Button("Add Reservartion");
        Button listReservartionBtn = new Button("Reservartion List");
        Button DeletePlanningBtn = new Button("Delete Planning");
        Button DeleteReservationBtn = new Button("Delete Reservation");

        
//        addPlanningBtn.addActionListener(e -> new AddPlanningForm().show());
//        listPlanningBtn.addActionListener(x-> new ListPlanningForm().show());
//        addReservartionBtn.addActionListener(e -> new AddReservationForm().show());
//        listReservartionBtn.addActionListener(x-> new ListReservationForm().show());
//        DeletePlanningBtn.addActionListener(e-> new DeletePlanningForm().show());
//        DeleteReservationBtn.addActionListener(e-> new DeleteReservationForm().show());

        
        
        this.addAll(addPlanningBtn, listPlanningBtn,addReservartionBtn,listReservartionBtn,DeletePlanningBtn,DeleteReservationBtn);
        
    }
    
}

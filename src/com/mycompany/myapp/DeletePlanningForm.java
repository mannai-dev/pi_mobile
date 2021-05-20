/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.PlanningService;


/**
 *
 * @author administrater
 */
public class DeletePlanningForm extends BaseForm{
      
    public DeletePlanningForm(Resources res) {
         super.addSideMenu(res);
        setTitle("Delete Planning");
        setLayout(BoxLayout.y());
        TextField idPlanning = new TextField("","Planning id");
         Button btnValide = new Button("Delete Planning");
        addAll(idPlanning,btnValide);
        btnValide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PlanningService PlanningsService=new PlanningService();
                PlanningsService.deleteItem(Integer.parseInt(idPlanning.getText()));
            }
        });
           
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());    }

   
}

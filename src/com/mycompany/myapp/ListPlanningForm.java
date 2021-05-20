/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.services.PlanningService;

/**
 *
 * @author administrater
 */
public class ListPlanningForm extends BaseForm{
 
    public ListPlanningForm(Resources res){
         super.addSideMenu(res);
        this.setTitle("Plannigs List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel planningsListSP = new SpanLabel();
        planningsListSP.setText(PlanningService.getInstance().getPlannings().toString());
        
        this.add(planningsListSP);
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        Button btn1 = new Button("add");
        Button btn2 = new Button("delete");
        
             btn1.addActionListener((e) -> {
            new AddPlanningForm(res).show();

        });
         
        btn2.addActionListener((e) -> {
            new DeletePlanningForm(res).show();

        });
       Container content = BoxLayout.encloseY(
                new Label("Order", "LogoLabel"),
                
                createLineSeparator(),
                btn1,btn2
               
        );
   this.add(content);
    }
        
        
        
    }

    

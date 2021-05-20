/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import Services.PlanningService;
/**
 *
 * @author administrater
 */
public class ListPlanningForm extends Form{
 
    public ListPlanningForm(){
        
        this.setTitle("Plannigs List");
        this.setLayout(BoxLayout.y());
        
        SpanLabel planningsListSP = new SpanLabel();
        planningsListSP.setText(PlanningService.getInstance().getPlannings().toString());
        
        this.add(planningsListSP);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
    }

    
}

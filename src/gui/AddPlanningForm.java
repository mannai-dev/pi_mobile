/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import static java.lang.Integer.parseInt;
import models.Planning;
import Services.PlanningService;

public class AddPlanningForm extends Form{
    
    public AddPlanningForm(){
        this.setTitle("Add Planning");
        this.setLayout(BoxLayout.y());
        
        TextField tfidCoach = new TextField("", "Insert coach id");
        TextField tfcoachName = new TextField("", "Insert coach name ");
        TextField tfcourse = new TextField("", "Insert course");
        TextField tfstartLesson = new TextField("", "Insert start lesson ");
        TextField tfendLesson = new TextField("", "Insert end lesson");
        TextField tfmaxnbr = new TextField("", "Insert max number");
        TextField tfnbrActuel = new TextField("", "Insert actuel number");
        
        
        Button submitPlanningBtn = new Button("Submit");
        submitPlanningBtn.addActionListener((evt) -> {
            
            if (tfidCoach.getText().length() ==0 || tfcoachName.getText().length()==0 || tfcourse.getText().length()==0 || tfstartLesson.getText().length()==0 || tfendLesson.getText().length()==0 || tfmaxnbr.getText().length()==0 || tfnbrActuel.getText().length()==0) {
                Dialog.show("Alert", "Textfields cannot be empty.",null, "OK");
            }else {
                
                try {
                    
                    Planning planning = new Planning(Integer.parseInt(tfidCoach.getText()),tfcoachName.getText(),tfcourse.getText(),tfstartLesson.getText(),tfendLesson.getText(),Integer.parseInt(tfmaxnbr.getText()),Integer.parseInt(tfnbrActuel.getText()));
                    if (PlanningService.getInstance().addPlanningAction(planning)) {
                        Dialog.show("Success", "planning added successfully.",null, "OK");
                    }
                    
                } catch (NumberFormatException e) {
                    Dialog.show("Alert", "planning's status must be a number.",null, "OK");
                }
                
                
                
            }
            
            
            
        });
        
        this.addAll(tfidCoach, tfcoachName,tfcourse,tfstartLesson,tfendLesson,tfmaxnbr,tfnbrActuel, submitPlanningBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev-> new HomeForm().showBack());
        
        
    }
    
}

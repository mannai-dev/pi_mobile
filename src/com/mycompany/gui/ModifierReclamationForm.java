/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author ASUS
 */
public class ModifierReclamationForm extends BaseForm{
    
    Form current;
    public ModifierReclamationForm(Resources res,Reclamation r){
        
         super("Newsfeed",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        TextField title =new TextField(r.getTitle(),"Title",20,TextField.ANY);
        TextField description =new TextField(r.getDescription(),"Description",20,TextField.ANY);
        
        
        title.setUIID("NewsTopLine");
        description.setUIID("NewsTopLine");
        
        title.setSingleLineTextArea(true);
        description.setSingleLineTextArea(true);
        
        Button btnModifier=new Button("Modifier");
        btnModifier.setUIID("Button");
        
        btnModifier.addPointerPressedListener(l->{
        r.setTitle(title.getText());
        r.setDescription(description.getText());
        
        
        
        
        if(ServiceReclamation.getInstance().modifierReclamation(r)){
        new ListReclamationForm(res).show();
        }
        });
        Button btnAnnuler=new Button("Annuler");
        
        btnAnnuler.addPointerPressedListener(e->{
        new ListReclamationForm(res).show(); 
        });
        
        Label l1=new Label("");
        Label l2=new Label("");
        Label l3=new Label("");
        Label l4=new Label("");
        Label l5=new Label("");
        
        Container content = BoxLayout.encloseY(l1,l2,new FloatingHint(title),createLineSeparator(),new FloatingHint(description),createLineSeparator(),btnModifier,btnAnnuler);
        
        
        add(content);
        show();
        
        
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Events;
import com.mycompany.services.EventService;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author BALI
 */
public class ListEventForm extends BaseForm{
    
    Form current;
    public ListEventForm(Resources res){
        super("Newsfeed",BoxLayout.y());
        Toolbar tb=new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        super.addSideMenu(res);
        
        getContentPane().setScrollVisible(false);
        
        tb.addSearchCommand((e)->{
        
        
        
        });
        
        Tabs swipe=new Tabs();
        Label s1= new Label();
        Label s2= new Label();
        
        //addTab(swipe,s1,res.getImage("back-logo.jpeg"),"","",res);
        
        //
        
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("My events", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
  
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste),
                FlowLayout.encloseBottom(arrow)
        ));
      
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
  
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
 
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
        ArrayList<Events>list = EventService.getInstance().Display();
        
        for(Events rec : list){
            
            String urlImage = "smily.png";
            
            Image placeHolder=Image.createImage(120,90);
            EncodedImage enc=EncodedImage.createFromImage(placeHolder,false);
            URLImage urlim=URLImage.createToStorage(enc, urlImage, urlImage,URLImage.RESIZE_SCALE);
        
        addButton(urlim,rec,res);
        
        ScaleImageLabel image=new ScaleImageLabel(urlim);
        
        Container containerImg=new Container();
        
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        }
        
    }
    
        private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
/*
    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight()<size){
            image = image.scaledHeight(size);
        }
        if(image.getHeight()>Display.getInstance().getDisplayHeight()/2){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() /2);
        }
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overLay=new Label("","ImageOverlay");
        
        Container page1=LayeredLayout.encloseIn(imageScale,overLay,BorderLayout.south(BoxLayout.encloseY(new SpanLabel(text,"LargeWhiteText"),FlowLayout.encloseIn(null),spacer)));
        swipe.addTab("",res.getImage("back-logo.jpeg"),page1);
    }
*/
    
    public void bindButtonSelection(Button btn, Label l){
    btn.addActionListener(e->{
    if(btn.isSelected()){
    updateArrowPosition(btn,l);
    }
    
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX()+btn.getWidth()/2-l.getWidth()/2);
        l.getParent().repaint();
    }

    private void addButton(Image img, Events rec,Resources res) {
        
        int height=Display.getInstance().convertToPixels(11.5f);
        
        int width=Display.getInstance().convertToPixels(14f);
        
        Button image=new Button(img.fill(width, height));
        image.setUIID("Label");
        
        Container cnt= BorderLayout.west(image);
       // TextArea ta=new TextArea(title);
       // ta.setUIID("NewsTopLine");
       // ta.setEditable(false);
       Label titleTxt=new Label("Title :"+rec.getNameEve(),"NewsTopLine2");
       Label descriptionTxt=new Label("description :"+rec.getDescriptionEve(),"NewsTopLine2");
       Label etatTxLabel=new Label();
       
       createLineSeparator();
       
       
      
       
       
        
       
        Label lSupprimer=new Label(" ");
        lSupprimer.setUIID("NewsTopLine");
        Style supprimerStyle=new Style(lSupprimer.getUnselectedStyle());
        supprimerStyle.setFgColor(0xf21f1f);
        
        FontImage supprimerImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
        lSupprimer.setIcon(supprimerImage);
        lSupprimer.setTextPosition(RIGHT);
        
        
        lSupprimer.addPointerPressedListener(l -> {
        Dialog dig=new Dialog("Suppression");
        
        if(dig.show("Suppression","Vous voullez supprimer cette evenement ?","Annuler","Oui")){
        dig.dispose();
        
        }else{
        dig.dispose();
           if(EventService.getInstance().deleteReclamation(rec.getIdEvent())){
           new ListEventForm(res).show();
           
           }
        
        }
        
        });
        
        Label lModifier=new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle=new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0xf7ad02);
        
        FontImage mFontImage=FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);
        
        
        lModifier.addPointerPressedListener(l -> {
//        new ModifierReclamationForm(res,rec).show();
        
        });
        
        
        
        
         cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(titleTxt),BoxLayout.encloseX(descriptionTxt),BoxLayout.encloseX(etatTxLabel,lModifier,lSupprimer)));
        
        add(cnt);
    }
            Button pdf = new Button("Print");


    
}

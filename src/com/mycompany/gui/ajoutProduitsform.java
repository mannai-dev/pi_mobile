/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServicesProduits;
import com.sun.mail.smtp.SMTPTransport;
import entities.Produits;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author LeNoVo
 */
public class ajoutProduitsform extends BaseForm {
    TextField email;
    Form current;
public  ajoutProduitsform(Resources res){
  super("Newsfeed",BoxLayout.y());
  Toolbar tb=new Toolbar(true);
  current=this;
  setToolbar(tb);
  getTitleArea().setUIID("Container");
  setTitle("ajouter produit");
  getContentPane().setScrollVisible(false);
  tb.addSearchCommand((e)->{
        
        
        
        });
  Tabs swipe=new Tabs();
        Label s1= new Label();
        Label s2= new Label();
        
        addTab(swipe,s1,res.getImage("back-logo.jpeg"),"","",res);
        
        
         swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("les produits", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("partenaires", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        //  ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        
        
  
  
  
  
  
  
  
  
  
  TextField DescriptionProd =new TextField("","descprod:");
  DescriptionProd.setUIID("TextFieldBlack");
  addStringValue("DescriptionProd",DescriptionProd);
  
  TextField photoProd =new TextField("","photoProd:");
  photoProd.setUIID("TextFieldBlack");
  addStringValue("photoProd",photoProd);
  
  TextField title =new TextField("","title:");
  title.setUIID("TextFieldBlack");
  addStringValue("title",title);
  
  TextField price =new TextField("","price:");
  price.setUIID("TextFieldBlack");
  addStringValue("price",price);
  
   TextField stock =new TextField("","stock:");
  stock.setUIID("TextFieldBlack");
  addStringValue("stock",stock);
  
   TextField idVendor =new TextField("","idVendor:");
  idVendor.setUIID("TextFieldBlack");
  addStringValue("idVendor",idVendor);
  
   email = new TextField("","entrez votre email",20,TextField.ANY);
   email.setSingleLineTextArea(false);
   email.setUIID("TextFieldBlack");
    addStringValue("email",email);
  
  
  
  Button btnajouter= new Button("ajouter");
  addStringValue("",btnajouter);
  
  btnajouter.addActionListener((e) -> {
      try{
          if(title.getText().equals("") || price.getText().equals("")){
            Dialog.show("Veuillez verifier les donnees","", "Annuler", "OK");
          
      }else{
              InfiniteProgress ip=new InfiniteProgress();
              final Dialog iDialog =ip.showInfiniteBlocking();
              String text =price.getText();
              int pr = Integer.parseInt(text);
              
              String textidv =idVendor.getText();
              int idv = Integer.parseInt(textidv);
              
              String textst =stock.getText();
              int st = Integer.parseInt(textst);
              
              
              
              Produits p= new Produits(st,idv,pr,String.valueOf(photoProd.getText()).toString(),String.valueOf(DescriptionProd.getText()).toString(),String.valueOf(title.getText()).toString()
                                                                    );
              System.out.println("data produits=="+p);
              sendmail();
              ServicesProduits.getInstance().ajouterProduits(p);
              iDialog.dispose();
              
              new listProduitsform(res).show();
              
              refreshTheme();
              
              
              
          }
  }catch(Exception ex){
            ex.printStackTrace();
        
        }
  
    
    
});
}

    private void addStringValue(String s, Component v) {
         add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
         
    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
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
        
        Container page1 =
                LayeredLayout.encloseIn(
                imageScale,
                        overLay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text,"LargeWhiteText"),
                                                   spacer
                            )
                        )
                );
                        swipe.addTab("",res.getImage("back-logo.jpeg"),page1);
                        
                        
                
    }

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

public void sendmail(){
    
    try{
     
        Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication
                
                Session session = Session.getInstance(props,null);
                
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("workit.noreplay2021@gmail.com"));
                msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
                msg.setSubject("produit ajouté");
                //msg.setSentDate(new date(System.currentTimeMillis()));
                String txt =" votre produit a éte ajouté avec succes";
                msg.setText(txt);
                
                
                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
                //st.connect("smtp.gmail",465,"workit.noreplay2021@gmail.com","workit.noreplay2021@gmail.com w");
                st.connect("smtp.gmail.com", 465, "work.itpidev@gmail.com", "Workit2021");
                st.sendMessage(msg, msg.getAllRecipients());
                System.out.println("server response"+st.getLastServerResponse());
                
                
    }catch(Exception e) {
        e.printStackTrace();
                
      }
    
    
}

    
    
}
    
    


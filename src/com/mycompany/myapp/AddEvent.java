/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ToastBar;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.services.EventService;

import java.io.IOException;
import java.util.Vector;

/**
 * AddEvent UI
 *
 * @author Shai Almog
 */
public class AddEvent extends BaseForm {

    private String filename = "";
    private String filepath = "";
    private boolean dialog=false;

    public AddEvent(Resources res) {
        
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");
        super.addSideMenu(res);
        TextField eventn = new TextField("", "Event name", 20, TextField.ANY);
        TextArea description = new TextField("", "Description", 360, TextArea.ANY);

        eventn.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
EventService es = new EventService();
        Button browse = new Button("Browse");
        Button add = new Button("Add Event");
        Button cancel = new Button("Cancel");
        cancel.addActionListener(e -> previous.showBack());
        cancel.setUIID("Link");
      //  Label alreadHaveAnAccount = new Label("Vous avez déjà un compte ?");
        Label ivv = new Label(res.getImage("Logo.png"), "LogoLabel");
        Dimension d = new Dimension(512, 512);
        ivv.setMaxAutoSize(BOTTOM);
        Container content = BoxLayout.encloseY(
                new Label("Ajout Event", "LogoLabel"),
                new FloatingHint(eventn),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                createLineSeparator(),
                browse, ivv,
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                add
              
        ));
        add.requestFocus();

        browse.addActionListener((evt) -> {
            ActionListener callback = e -> {
                if (e != null && e.getSource() != null) {
                    filepath = (String) e.getSource();
                    try {
                        Image img = Image.createImage(filepath).scaledWidth(Math.round(Display.getInstance().getDisplayWidth() / 2));
//                        ImageViewer iv = new ImageViewer(img);
//                        add(BorderLayout.CENTER_BEHAVIOR_SCALE,iv);
                        ivv.setIcon(img);
                        int fileNameIndex = filepath.lastIndexOf("/") + 1;
                        filename = filepath.substring(fileNameIndex);
                        revalidate();

                    } catch (IOException ex) {

                    }
                }
            };
            Display.getInstance().openGallery(callback, Display.GALLERY_IMAGE);
        });
        add.addActionListener((evt) -> {

            if (eventn.getText().equals("") || description.getText().equals("") || filename.equals("")) {
                dialog = true;
                ToastBar.showErrorMessage("Veuillez remplir tout les champs");
            } else {
                if(es.addeve(eventn, description,filename).equals("Success")){
                    Dialog.show("Success", "Done !", "Ok", null);
                    new ListEventForm(res).show();
                
                refreshTheme();
                }
                new EventsFrom(res).show();

            }
            if (dialog) {
                Dialog.show("Erreur", "Echec de la création du compte", "ok", null);
                //
            }
            
        });
        
          
    }

}

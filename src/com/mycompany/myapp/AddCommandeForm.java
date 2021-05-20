/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceCommande;
import com.mycompany.services.ServicePanier;
import java.util.Vector;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author moham
 */
public class AddCommandeForm extends BaseForm {

    Form current;
    private Resources theme;

    public AddCommandeForm(Resources res) {

        super(new BorderLayout());
        current = this;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        super.addSideMenu(res);
        Form previous = Display.getInstance().getCurrent();
        
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField adresse = new TextField("", "Adresse de livraison", 20, TextField.ANY);
        TextField total = new TextField("", "Total", 20, TextField.ANY);

        adresse.setSingleLineTextArea(false);
        total.setSingleLineTextArea(false);

        //  Button commander = new Button("Commander");
        Button next = new Button("Order");
//        commander.addActionListener(e -> new AddCommandeForm(res).show ());
//        commander.setUIID("Link");
//       

        Button btnListOrder = new Button("Order List");

        //   btnListOrder.addActionListener(e -> new ListCommandeForm(current).show());
        //   addAll(btnAddTask, btnListTasks);
        Container content = BoxLayout.encloseY(
                new Label("Order", "LogoLabel"),
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(adresse),
                createLineSeparator(),
                new FloatingHint(total)
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                btnListOrder
        ));
        next.requestFocus();
        next.addActionListener(e -> {
            
            ServiceCommande.getInstance().addCom(email,adresse, total, res);
            

        });
        btnListOrder.addActionListener(e -> {

          
            theme = UIManager.initFirstTheme("/theme");
            Form hi = new ListCommandeForm(theme);
            hi.show();
            ServiceCommande sc = new ServiceCommande();
            System.out.println(sc.OrderList());
            
        });
    }
}

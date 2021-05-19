
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.mycompany.gui.ListReclamationForm;




public class EmailProfil extends Demo {

    public EmailProfil(Resources res) {

    }

    public String getDisplayName() {

        return "Mail";
    }

    public Image getDemoIcon() {
        return getResources().getImage("mail.png");
    }

    public Container createDemo(Resources res) {

        Container message = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        ComponentGroup gp = new ComponentGroup();
        message.addComponent(gp);

        final TextField to = new TextField("");
        to.setHint("TO:");
        gp.addComponent(to);

        final TextField subject = new TextField("");
        subject.setHint("Subject");
        gp.addComponent(subject);
        final TextField body = new TextField("");
        body.setRows(4);
        body.setHint("Message Body");
        gp.addComponent(body);

        ComponentGroup buttonGroup = new ComponentGroup();
        Button btn = new Button("Send");
        Button btn2 = new Button("return");
        buttonGroup.addComponent(btn);
        buttonGroup.addComponent(btn2);
        btn2.addActionListener((e) -> {
            new ListReclamationForm(res).show();

        });
        message.addComponent(buttonGroup);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Message msg = new Message(body.getText());
                Display.getInstance().sendMessage(new String[]{to.getText()}, subject.getText(), msg);
            }
        });

        return message;
    }

    @Override
    public Container createDemo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author mortadha
 */

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.User;
import com.mycomany.services.ServiceUser;
import static com.pidevv.MyApplication.verificationcode;
import static com.pidevv.MyApplication.verificationemail;
import static com.pidevv.MyApplication.Resetuser;

import java.util.Random;

public class ForgotPassordForm extends BaseForm {

    public ForgotPassordForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);

        email.setSingleLineTextArea(false);

        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Send Verification Code", "LogoLabel"),
                new FloatingHint(email),
                createLineSeparator()
        );
        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                next,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        ));
        next.requestFocus();
        next.addActionListener(e -> {
            Random rand = new Random();
            int randomCode = rand.nextInt(999999);
            verificationcode = randomCode;
            verificationemail = email.getText();
            //
            User user;
            user = ServiceUser.getInstance().veriferemail(verificationemail);
            // System.out.println(user);
            if (user == null) {
                ToastBar.showMessage("Probleme  user not found", FontImage.MATERIAL_INFO);
            } else {
                ToastBar.showMessage(" user  found", FontImage.MATERIAL_INFO);
               Resetuser = user;
                ServiceUser.getInstance().sendEmail(verificationemail, verificationcode);
                ToastBar.showMessage("Verification code was sent to your email", FontImage.MATERIAL_INFO);
                new VerifierCodeForm(res).show();

            }

            //
        });
    }

}

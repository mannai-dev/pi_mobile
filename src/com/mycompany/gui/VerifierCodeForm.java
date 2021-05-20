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
import com.codename1.components.SpanLabel;
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
import com.mycomany.services.ServiceUser;
import static com.pidevv.MyApplication.verificationcode;
import static com.pidevv.MyApplication.verificationemail;

import java.util.Random;

/**
 * Account activation UI
 *
 * @author Shai Almog
 */
public class VerifierCodeForm extends BaseForm {

    public VerifierCodeForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");

        add(BorderLayout.NORTH,
                BoxLayout.encloseY(
                        new Label(res.getImage("smily.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );

        TextField code = new TextField("", "Enter Code", 20, TextField.NUMERIC);
        code.setSingleLineTextArea(false);

        Button signUp = new Button("Verify");
        Button resend = new Button("Resend");
        resend.setUIID("CenterLink");
        Label alreadHaveAnAccount = new Label("Already have an account?");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("CenterLink");

        Container content = BoxLayout.encloseY(
                new FloatingHint(code),
                createLineSeparator(),
                new SpanLabel("We've sent the confirmation code to your email. Please check your inbox", "CenterLabel"),
                resend,
                signUp,
                FlowLayout.encloseCenter(alreadHaveAnAccount, signIn)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        signUp.requestFocus();

        resend.addActionListener(e -> {
            Random rand = new Random();
            int randomCode = rand.nextInt(999999);
            verificationcode = randomCode;
            ServiceUser.getInstance().sendEmail(verificationemail, verificationcode);
            ToastBar.showMessage("Verification code was sent to your email", FontImage.MATERIAL_INFO);

        });

        signUp.addActionListener(e -> {
            if (code.getText().equalsIgnoreCase(String.valueOf(verificationcode))) {
                new ResetPasswordForm(res).show();
            } else {
                ToastBar.showMessage("Wrong Code", FontImage.MATERIAL_INFO);
            }

        });
    }

}

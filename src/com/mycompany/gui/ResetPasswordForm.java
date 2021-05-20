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
import static com.pidevv.MyApplication.Resetuser;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class ResetPasswordForm extends BaseForm {

    public ResetPasswordForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField confirmPassword = new TextField("", "Confirm Password", 20, TextField.PASSWORD);

        password.setSingleLineTextArea(false);
        confirmPassword.setSingleLineTextArea(false);
        Button next = new Button("Reset Password");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(password),
                createLineSeparator(),
                new FloatingHint(confirmPassword),
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

            if (password.getText().equalsIgnoreCase(confirmPassword.getText())) {

                User user;
                Resetuser.setPassword(password.getText());
                user = ServiceUser.getInstance().resetpassword(Resetuser);
                if (user == null) {
                    ToastBar.showMessage("Probleme connexion ", FontImage.MATERIAL_INFO);
                } else {
                    ToastBar.showMessage("Password reseted", FontImage.MATERIAL_INFO);
                    new SignInForm(res).show();
                }

            } else {
                ToastBar.showMessage("Verify Password written ", FontImage.MATERIAL_INFO);
            }
        }
        );
    }

   /* public String encryptThisString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }*/

}

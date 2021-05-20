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

package com.mycompany.gui;

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


import com.mycomany.entities.User;
import com.mycomany.services.ServiceUser;
import com.codename1.capture.Capture;
import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.io.IOException;
import java.io.OutputStream;
import static com.pidevv.MyApplication.Sessionuser;
import static com.pidevv.MyApplication.iduser;
import static com.pidevv.MyApplication.signupuser;
import static com.pidevv.MyApplication.verificationcode;
import java.util.Random;

/**
 * Signup UI
 *
 * @author Shai Almog
 */
public class SignUpForm extends BaseForm {

    String fileName = null, imgtype;

    public SignUpForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        TextField username = new TextField("", "username", 20, TextField.ANY);
        TextField password = new TextField("", "password", 20, TextField.PASSWORD);
        TextField FirstName = new TextField("", "FirstName", 20, TextField.ANY);
        TextField LastName = new TextField("", "LastName", 20, TextField.ANY);
        TextField Adresse = new TextField("", "Adresse", 20, TextField.ANY);
        TextField tel = new TextField("", "telephone", 20, TextField.PHONENUMBER);
        TextField email = new TextField("", "email", 20, TextField.EMAILADDR);
        //valdator email
        Validator validator = new Validator();
        validator.addConstraint(email, RegexConstraint.validEmail());
        RegexConstraint emailConstraint = new RegexConstraint("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$", "Invalid Email Address");
        validator.addConstraint(email, emailConstraint);
        ComboBox role = new ComboBox<>();
        role.addItem("Member");
        role.addItem("Vendor");
        role.addItem("Coach");
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        tel.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        FirstName.setSingleLineTextArea(false);
        LastName.setSingleLineTextArea(false);
        Adresse.setSingleLineTextArea(false);
        Button next = new Button("Next");
        Button signIn = new Button("Sign In");
        signIn.addActionListener(e -> previous.showBack());
        signIn.setUIID("Link");
        Label alreadHaveAnAccount = new Label("Already have an account?");
//upload

        Button btnCapture = new Button("Upload Image");
        Label limage = new Label();
        limage.setHeight(150);
        limage.setWidth(150);

        btnCapture.addActionListener((e) -> {
            String path = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
            // System.out.println(path);
            if (path != null) {
                try {
                    int height = Display.getInstance().convertToPixels(31.5f);
                    int width = Display.getInstance().convertToPixels(34f);

                    Image img = Image.createImage(path);
                    System.out.println(path);
                    int fileNameIndex = path.lastIndexOf("/") + 1;
                    fileName = path.substring(fileNameIndex, path.length() - 5);
                    System.out.println(fileName);

                    int fileNameIndex1 = path.lastIndexOf(".") + 1;
                    imgtype = path.substring(fileNameIndex1, path.length());
                    System.out.println(imgtype);
                    System.out.println(path);
                    fileName += "." + imgtype;
                    System.out.println(fileName);

                    limage.setIcon(img.fill(width, height));
                    OutputStream os = FileSystemStorage.getInstance().openOutputStream(FileSystemStorage.getInstance().getAppHomePath() + fileName);

                    if (imgtype.equalsIgnoreCase("jpg")) {
                        ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                    } else {
                        ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 0.9f);
                    }
                    os.close();
                    this.revalidate();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
//upload

        Container content = BoxLayout.encloseY(
                new Label("Sign Up", "LogoLabel"),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(password),
                 new FloatingHint(FirstName),
                createLineSeparator(),
                new FloatingHint(LastName),
                createLineSeparator(),
                new FloatingHint(Adresse),
                createLineSeparator(),
                new FloatingHint(tel),
                createLineSeparator(),
                BoxLayout.encloseX(BoxLayout.encloseX(btnCapture, limage)),
                createLineSeparator(),
                new FloatingHint(email),
                createLineSeparator(),
                role,
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
            Boolean verifier = true;
            if (username.getText().length() == 0 || password.getText().length() == 0 || FirstName.getText().length() == 0 || LastName.getText().length() == 0
                    || email.getText().length() == 0 || tel.getText().length() == 0 || role.getSelectedItem().toString().length() == 0
                    || Adresse.getText().length() == 0 || fileName == null) {
                verifier = false;
                ToastBar.showMessage("don't leave empty field", FontImage.MATERIAL_INFO);
            } else if (Valider_MDP(password.getText()) == false) {
                verifier = false;
                ToastBar.showMessage("mot de passe need 1 min 1 maj and numbers", FontImage.MATERIAL_INFO);
            } else if (!validator.isValid()) {
                verifier = false;
                ToastBar.showMessage("email format warning", FontImage.MATERIAL_INFO);
            }

            if (verifier == true) {
                float tell = Float.parseFloat(tel.getText());
                User u = new User(username.getText(), password.getText(), FirstName.getText(),LastName.getText(),
                        Adresse.getText(),(int) tell, email.getText() ,fileName, role.getSelectedItem().toString());
                signupuser = u;
                
                System.out.println(u);
                //mail
                Random rand = new Random();
                int randomCode = rand.nextInt(999999);
                verificationcode=randomCode;
                ServiceUser.getInstance().sendEmail2(signupuser.getEmail(), verificationcode);

                //
                ToastBar.showMessage("Success", FontImage.MATERIAL_INFO);

                new ActivateForm(res).show();

            } else {
                ToastBar.showMessage("Probleme saisie", FontImage.MATERIAL_INFO);
            }

            /*  float tell = Float.parseFloat(tel.getText());
            try {
                Users u = new Users(username.getText(), password.getText(), sexe.getSelectedItem().toString(),
                        email.getText(), (int) tell, domaine.getSelectedItem().toString(), datePicker.getDate(),
                        encryptThisString(password.getText()), fileName);
                System.out.println(u);
               if(verifier(u)==true){
                signupuser=u;
               
               }
               else{
                   
               } 
              /*  if (ServiceUsers.getInstance().addUser(u)) {
                         
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                    // new Front_MesEntreprise(res, null, null).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            } catch (NumberFormatException ee) {
                Dialog.show("ERROR", "Status must be a number", new Command("OK"));
            }*/
            //     new ActivateForm(res).show();
        });
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

    public boolean Valider_MDP(String Mdp) {
        if ((Compter_NB_MAJUS(Mdp) > 0) && (Compter_NB_MINUS(Mdp) > 0) && (Compter_NB_CHIFFRES(Mdp) > 0)) {
            return true;
        }
        return false;
    }

    public int Compter_NB_MAJUS(String Mdp) {
        int Cpt = 0, i;

        for (i = 0; i < Mdp.length(); i++) {
            if (Character.isUpperCase(Mdp.charAt(i))) {
                Cpt++;
            }

        }
        return Cpt;
    }

    public int Compter_NB_MINUS(String Mdp) {
        int Cpt = 0, i;

        for (i = 0; i < Mdp.length(); i++) {
            if (Character.isLowerCase(Mdp.charAt(i))) {
                Cpt++;
            }

        }
        return Cpt;
    }

    public int Compter_NB_CHIFFRES(String Mdp) {
        int Cpt = 0, i;

        for (i = 0; i < Mdp.length(); i++) {
            if (Mdp.charAt(i) >= '0' && Mdp.charAt(i) <= '9') {
                Cpt++;
            }
        }
        return Cpt;
    }

}

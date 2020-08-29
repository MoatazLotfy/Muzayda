/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.person;
import aucation.user;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.animation.ParallelTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 *
 * @author Moataz
 */
public class FXMLDocumentController {
     person persn = new person();
    user usr = new user();
    Stage stage;
    Parent root;
    FXMLLoader loader;
    public Button LoginBTN, SignupBTN,exit;
    public Label p1, p2, p3, p4;
    public TextField  UserTXT;
    public PasswordField PassTXT;
    public String userType = persn.publicType; 
   
     public void loadscene(String fxmlname, Button bot) {

        stage = (Stage) bot.getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource(fxmlname));
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadstage() {
        Scene scene = new Scene(root, 1000, 650);
        stage.setScene(scene);
        stage.show();
    }
    public void exit(){
    Stage e = (Stage) exit.getScene().getWindow();
    // do what you have to do
    e.close();
 }
    
        public void LoginBtnClc() {
        p1.setStyle("-fx-text-fill: #188c90");
        p2.setStyle("-fx-text-fill: #188c90");
        p3.setStyle("-fx-text-fill: #188c90");
        p4.setStyle("-fx-text-fill: #188c90");
        LoginBTN.setStyle("-fx-border-color: grey; -fx-text-fill: grey; -fx-translate-x: -30");
        LoginBTN.setText("Logging in");
        LoginBTN.setDisable(true);
        TranslateTransition t1, t2, t3, t4;

        t1 = new TranslateTransition();
        t2 = new TranslateTransition();
        t3 = new TranslateTransition();
        t4 = new TranslateTransition();
        t1.setDuration(Duration.millis(860));
        t2.setDuration(Duration.millis(740));
        t3.setDuration(Duration.millis(620));
        t4.setDuration(Duration.millis(500));
        t1.setNode(p1);
        t2.setNode(p2);
        t3.setNode(p3);
        t4.setNode(p4);
        t1.setToY(-20);
        t2.setToY(-20);
        t3.setToY(-20);
        t4.setToY(-20);
        t1.setAutoReverse(true);
        t2.setAutoReverse(true);
        t3.setAutoReverse(true);
        t4.setAutoReverse(true);
        t1.setCycleCount(4);
        t2.setCycleCount(4);
        t3.setCycleCount(4);
        t4.setCycleCount(4);

        ParallelTransition par1 = new ParallelTransition(t1, t2, t3, t4);
        par1.play();
        par1.setOnFinished(e -> {

            LoginBTN.setStyle("-fx-border-color: #188c90; -fx-text-fill: #188c90");
            LoginBTN.setText("Login");
            LoginBTN.setDisable(false);
            p1.setStyle("-fx-text-fill: transparent");
            p2.setStyle("-fx-text-fill: transparent");
            p3.setStyle("-fx-text-fill: transparent");
            p4.setStyle("-fx-text-fill: transparent");

            /* loding other scene ---------
             ----------------------------------------------------------------------------*/
            try {
                persn.login(UserTXT.getText(), PassTXT.getText());
                //System.out.println(id + user.username + user.password + user.phone + user.visa + user.email + user.address + user.type);

            } catch (Exception e1) {

            }
            //Check username and password
            if (UserTXT.getText().equals(persn.publicUserName) && PassTXT.getText().equals(persn.publicPassword)) {
                loadscene("/aucation/FXML/Profile.fxml", LoginBTN);
               
                
                set_profile_data();    //call function that have user profile data.
                loadstage();
                
            }else{
           LoginBTN.setStyle("-fx-border-color: #C40E0E; -fx-text-fill: #C40E0E; -fx-translate-x: -30");
           LoginBTN.setText("Login Failed");
            }

        });
        // animation E --------------------------
       
    }
     public void set_profile_data() {
       ProfileController c = (ProfileController) loader.getController();
       
       
        c.ENameTXT.setText(persn.publicUserName);
        c.EPassTXT.setText(persn.publicPassword);
        c.EEmailTXT.setText(persn.publicEmail);
        c.EPhoneTXT.setText(persn.publicPhone);
        c.ECityTXT.setText(persn.publicAddress);
        c.ECreditTXT.setText(persn.publicVisa);
        //System.out.println(publicID + persn.publicUserName + persn.publicPassword + persn.publicPhone + persn.publicVisa + persn.publicEmail + persn.publicAddress + persn.publicType);
    }
     public void ResetData(){
         LoginBTN.setStyle("-fx-border-color: #188c90; -fx-text-fill: #188c90");
    LoginBTN.setText("Login");
    LoginBTN.setDisable(false);
        
    
    }
     
     public void signupclick() {
        loadscene("/aucation/FXML/Signup.fxml", SignupBTN);
        loadstage();
    }
    
}

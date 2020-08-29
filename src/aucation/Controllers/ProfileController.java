/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;
import aucation.person;
import aucation.user;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
/**
 *
 * @author Moataz
 */
public class ProfileController implements Initializable{
    
    person persn = new person();
    user usr = new user();
    public static String Actived_Button = new String();
    public String phone = persn.publicPhone, visa = persn.publicVisa,password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress,imagedescription,imagepath;
    //----------------
    public Button  edit, saveedit,exit;
    public TextField  ENameTXT, EPassTXT, EEmailTXT, EPhoneTXT, ECityTXT, ECreditTXT;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    
 /*-----------------------------------------------------------------------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------------------------------------------------------------------*/

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
/*-----------------------------------------------------------------------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------------------------------------------------------------------*/
 public void exit(){
    Stage e = (Stage) exit.getScene().getWindow();
    // do what you have to do
    e.close();
 }
 
  public void editclick() {

      EPassTXT.setDisable(false);
        EEmailTXT.setDisable(false);
        EPhoneTXT.setDisable(false);
        ECityTXT.setDisable(false);
        ECreditTXT.setDisable(false);


        EPassTXT.setStyle("-fx-border-color: transparent transparent #188c90 transparent; ");
        EEmailTXT.setStyle("-fx-border-color: transparent transparent #188c90 transparent; ");
        EPhoneTXT.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
        ECityTXT.setStyle("-fx-border-color: transparent transparent #188c90 transparent; ");
        ECreditTXT.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");

    }
/*-----------------------------------------------------------------------------------------------------------------------------------------------------
    -------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void saveeditclick() throws SQLException {
        if(ValidateonSubmit(ECityTXT, EPassTXT, EPhoneTXT, ECreditTXT, EEmailTXT)){
        ENameTXT.setDisable(true);
        EPassTXT.setDisable(true);
        EEmailTXT.setDisable(true);
        EPhoneTXT.setDisable(true);
        ECityTXT.setDisable(true);
        ECreditTXT.setDisable(true);
        ENameTXT.setStyle("-fx-border-color: transparent transparent grey transparent;");
        EPassTXT.setStyle("-fx-border-color: transparent transparent grey transparent; ");
        EEmailTXT.setStyle("-fx-border-color: transparent transparent grey transparent; ");
        EPhoneTXT.setStyle("-fx-border-color: transparent transparent grey transparent;");
        ECityTXT.setStyle("-fx-border-color: transparent transparent grey transparent; ");
        ECreditTXT.setStyle("-fx-border-color: transparent transparent grey transparent;");

        persn.editProfile(ENameTXT.getText(), EPhoneTXT.getText(), EPassTXT.getText(), ECreditTXT.getText(), EEmailTXT.getText(), ECityTXT.getText());
        //set_profile_data();
    }
        else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Invalid information");
                alert.showAndWait();   
        }
    }
          public void validateTextfiled(TextField t){
      t.focusedProperty().addListener((arg0, oldValue, newValue) -> {
        if (!newValue) { 
            if(t.getText().length()== 0){
               
                
                t.setStyle("-fx-border-color: transparent transparent red transparent;");
            }
            if(t.getText().length()!= 0){
               
                
                t.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
            }
            
        }

    });}
              public void validateEmail(TextField t){
      t.focusedProperty().addListener((arg0, oldValue, newValue) -> {
        if (!newValue) { 
            if(t.getText().length()== 0 || !t.getText().matches("[a-zA-Z]+[a-zA-Z0-9]*((\\_|\\.)[a-zA-Z0-9]*)?\\@[a-zA-Z]+\\.[a-zA-Z]+")){
               
                
                t.setStyle("-fx-border-color: transparent transparent red transparent;");
            }
            else{
               
                
                t.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
            }
            
        }

    });}
         public boolean ValidateonSubmit(TextField t1,TextField t2,TextField t3,TextField t4,TextField t5){
        if(t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || !t5.getText().matches("[a-zA-Z]+[a-zA-Z0-9]*((\\_|\\.)[a-zA-Z0-9]*)?\\@[a-zA-Z]+\\.[a-zA-Z]+")) {
                 return false;
        }
        else
            return true;
         }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateTextfiled(EPassTXT);
        validateEmail(EEmailTXT);
        validateTextfiled(ECityTXT);
        validateTextfiled(ECreditTXT);
        validateTextfiled(EPhoneTXT);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.person;
import aucation.user;
import static aucation.person.publicID;
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

public class SignupController implements Initializable {

    person persn = new person();
    public String radiochoice;
    user usr = new user();
    public static String Actived_Button = new String();
    public String phone = persn.publicPhone, visa = persn.publicVisa, password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress, imagedescription, imagepath;
    //----------------
    public Button Coninuebutton, exit;
    public TextField usernameTXT, passwordTXT, phoneTXT, emailTXT, creditTXT, cityTXT;
    public TextArea feedbackTXT;
    public PasswordField PassTXT;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    public RadioButton BuyerRadio, SellerRadio;
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

    public void exit() {
        Stage e = (Stage) exit.getScene().getWindow();

        e.close();
    }

    public void set_profile_data() {
        ProfileController c = (ProfileController) loader.getController();
        c.ENameTXT.setText(person.publicUserName);
        c.EPassTXT.setText(person.publicPassword);
        c.EEmailTXT.setText(person.publicEmail);
        c.EPhoneTXT.setText(person.publicPhone);
        c.ECityTXT.setText(person.publicAddress);
        c.ECreditTXT.setText(person.publicVisa);
        System.out.println(publicID + person.publicUserName + person.publicPassword + person.publicPhone + person.publicVisa + person.publicEmail + person.publicAddress + person.publicType);
    }

    public void register() throws SQLException {
        if (ValidateonSubmit(usernameTXT, passwordTXT, phoneTXT, creditTXT, emailTXT, cityTXT)) {
           
           if (BuyerRadio.isSelected()) {
                radiochoice = "Bidder";
            } else if (SellerRadio.isSelected()) {
                radiochoice = "Seller";
            }
            System.out.println(radiochoice);
           usr.register(usernameTXT.getText(), phoneTXT.getText(), passwordTXT.getText(), creditTXT.getText(),
                    emailTXT.getText(), cityTXT.getText(),radiochoice);
            loadscene("/aucation/FXML/Profile.fxml", Coninuebutton);
            set_profile_data();
            loadstage();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid information");
            alert.showAndWait();
        }
    }

    public void validateTextfiled(TextField t) {
        t.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (t.getText().length() == 0) {

                    t.setStyle("-fx-border-color: transparent transparent red transparent;");
                }
                if (t.getText().length() != 0) {

                    t.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
                }

            }

        });
    }

    public void validateEmail(TextField t) {
        t.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (t.getText().length() == 0 || !t.getText().matches("[a-zA-Z]+[a-zA-Z0-9]*((\\_|\\.)[a-zA-Z0-9]*)?\\@[a-zA-Z]+\\.[a-zA-Z]+")) {

                    t.setStyle("-fx-border-color: transparent transparent red transparent;");
                } else {

                    t.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
                }

            }

        });
    }

    public boolean ValidateonSubmit(TextField t1, TextField t2, TextField t3, TextField t4, TextField t5, TextField t6) {
        if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || t4.getText().isEmpty() || t6.getText().isEmpty() || !t5.getText().matches("[a-zA-Z]+[a-zA-Z0-9]*((\\_|\\.)[a-zA-Z0-9]*)?\\@[a-zA-Z]+\\.[a-zA-Z]+")) {
            
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validateTextfiled(usernameTXT);
        validateEmail(emailTXT);
        validateTextfiled(cityTXT);
        validateTextfiled(creditTXT);
        validateTextfiled(phoneTXT);
        validateTextfiled(passwordTXT);
    }

}

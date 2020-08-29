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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
/**
 *
 * @author Moataz
 */

public class ShowMyProductsController {
    person persn = new person();
    user usr = new user();
    public static String Actived_Button = new String();
    public String phone = persn.publicPhone, visa = persn.publicVisa,password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress,imagedescription,imagepath;
    public Button exit;
    public GridPane SMPGrid;
    public Image image;
    public ImageView iv;
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
/**
 *
 * @author Moataz
 */

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.dataBase;
import aucation.mail;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import aucation.person;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Moataz
 */
public class MailController implements Initializable {

    public VBox mailpane;
    public Button exit;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    mail m = new mail();
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
        // do what you have to do
        e.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap hm = new HashMap();
        person p = new person();
        int id = p.publicID;
        hm = m.GetMails(id);
        Set set = hm.entrySet();
        Iterator ii = set.iterator();
        while (ii.hasNext()) {
            Map.Entry me = (Map.Entry) ii.next();
            Button b = new Button(me.getKey().toString() + "\n\t" + me.getValue().toString());
            b.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-text-fill: #31bfc4;-fx-min-width:660px;-fx-min-height:110px;");

            mailpane.getChildren().add(b);
        }
    }
}

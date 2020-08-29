/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.dataBase;
import aucation.person;
import aucation.report;
import aucation.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed Ashraf
 */
public class ReportPopUpController implements Initializable {

    public person persn = new person();
    public user usr = new user();
    menuController mc = new menuController();
    dataBase db = dataBase.getobj();
    Statement stmt = null;
    public static String Actived_Button = new String();
    public String phone = persn.publicPhone, visa = persn.publicVisa, password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress, imagedescription, imagepath;
    //----------------
    public Button Px, Pbid;
    public TextArea ReportReason;
    public Label PDescription, PHPrice;
    public TextField PBPrice;
    public AnchorPane popuppane;
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

    public void closepopUp() {
        Stage e = (Stage) Px.getScene().getWindow();
        e.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void report_reason() {
        /*try {
            //problem here get the last one not the item i was click onit
            stmt = db.conn.createStatement();
            String query3 = "insert into report (itemID,reportedID,reason) values (" + mc.getItem_id() + "," + persn.publicID + "," + "'" + ReportReason.getText() + "'" + ")";
            System.out.println("qury" + query3);
            stmt.executeUpdate(query3);
        } catch (SQLException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
         report r = new report();
         r.insertReport(ReportReason.getText());
    }

}

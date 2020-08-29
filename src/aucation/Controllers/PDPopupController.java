/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.Bidder;
import aucation.auctionSession;
import aucation.person;
import aucation.user;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Moataz
 */
public class PDPopupController implements Initializable {

    public person persn = new person();
    public user usr = new user();
    menuController m = new menuController();
    public static String Actived_Button = new String();
    public String phone = persn.publicPhone, visa = persn.publicVisa, password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress, imagedescription, imagepath;
    //----------------
    public Button Px, Pbid;
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
    auctionSession AS = new auctionSession();
    Bidder bidder = new Bidder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap hm = new HashMap();
        try {
        hm = AS.GetDiscribtionandprice(m.getItem_id());
        Set set = hm.entrySet();
        Iterator ii = set.iterator();
        Map.Entry me = (Map.Entry) ii.next();
        PDescription.setText(me.getValue().toString());
        PHPrice.setText(me.getKey().toString());
        } catch (Exception e) {
            System.out.println("error");
        }
        

    }

    public void PBidClick() {
        int newprice = Integer.parseInt(PBPrice.getText());
        int current = Integer.parseInt(PHPrice.getText());
        if (newprice > current) {
            bidder.Subscribe(m.getItem_id(), newprice);
            if (AS.checknormalbidder(m.getItem_id(), persn.publicID) == true) {
                AS.updatenormalbidder(m.getItem_id(), newprice, persn.publicID);
            } else {
                AS.insertnormal(m.getItem_id(), newprice, persn.publicID);
            }
            PHPrice.setText(PBPrice.getText());

        }

    }

    

}

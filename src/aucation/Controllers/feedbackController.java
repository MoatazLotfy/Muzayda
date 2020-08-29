/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;


import aucation.feedback;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.layout.VBox;

/**
 *
 * @author Moataz
 */
public class feedbackController {

    public Button feedback, Submitfeedback;
    public Label feedbackLBL;
    public TextArea feedbackTXT;
    public AnchorPane ba;
    public VBox mailpane;
    public Image image;
    public ImageView iv;
    int feedbackstatus = 1;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    int c=0;

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

    public void feedbackclick() {

        TranslateTransition t = new TranslateTransition();
        t.setDuration(Duration.millis(200));
        t.setNode(ba);

        if (feedbackstatus == 1) {
            t.setToY(600);
            t.play();

            feedbackstatus = 2;
        } else {
            t.setToY(0);
            t.play();

            feedbackstatus = 1;
        }

    }

    public void feedb() throws SQLException {
        try {
       //System.out.println("feedback ="+feedbackTXT.getText());
       //user usr = new user();
       //usr.fillFeedback(feedbackTXT.getText());
            if (c==0){
            feedback feed = new feedback(feedbackTXT.getText());
            feed.fillFeedback();
            c++;
            }else{
                System.out.println("u have one!");
            }
        } catch (Exception e) {
            System.out.println("wrooooooong!");
        }
        
    }

}

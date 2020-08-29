/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.Remove_user_enc;
import aucation.dataBase;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author mezo_
 */
public class ControlUserController implements Initializable {

    public VBox ControlUserVBox;
    public Button exit;
    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    ArrayList<Remove_user_enc> al_user;
    int user_count;
    ResultSet rs;
    ResultSet rs1;
    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/

    public void exit() {
        Stage e = (Stage) exit.getScene().getWindow();
        // do what you have to do
        e.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            stmt = db.conn.createStatement();
            String query = "SELECT COUNT(*) FROM user where user.type <> 'Admin'";
            rs = stmt.executeQuery(query);
            String user_no = rs.getString(1);
            user_count = Integer.parseInt(user_no);
            String query1 = "SELECT userName FROM user where user.type <> 'Admin'";
            rs1 = stmt.executeQuery(query1);
        } catch (SQLException ex) {
            Logger.getLogger(ControlUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        al_user = new ArrayList<>(user_count);
        for (int counter = 0; counter < user_count; counter++) {
            try {
                rs1.next();
                al_user.add(new Remove_user_enc(rs1.getString("userName")));
                
            } catch (SQLException ex) {
                Logger.getLogger(ControlUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (Remove_user_enc user : al_user) {
            ControlUserVBox.getChildren().add(user.getUser());
        }

    }

}
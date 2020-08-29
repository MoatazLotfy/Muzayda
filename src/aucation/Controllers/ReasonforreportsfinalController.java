/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.dataBase;
import aucation.report;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mohamed Ashraf
 */
public class ReasonforreportsfinalController implements Initializable {

    public Button Px;
    public VBox reasonforreportVbox;
    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    report r = new report();
    ShowReportedUsersController src = new ShowReportedUsersController();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResultSet rs1;
            rs1 = r.selectReport(src.getUsername());
            int r_count = r.ReasonOfReport_no(src.getUsername());
            for (int i = 0; i < r_count; i++) {
                rs1.next();
                Button b = new Button(rs1.getString("reason"));
                b.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-text-fill: #31bfc4;-fx-min-width:660px;-fx-min-height:110px;");
                reasonforreportVbox.getChildren().add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReasonforreportsfinalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closepopUp() {
        Stage e = (Stage) Px.getScene().getWindow();

        e.close();
    }

}

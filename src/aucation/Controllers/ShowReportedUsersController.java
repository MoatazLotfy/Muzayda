package aucation.Controllers;

import aucation.Report_enc;
import aucation.dataBase;
import aucation.report;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ShowReportedUsersController implements Initializable {

    public Button exit;
    public VBox showreporteduserspane;
    public Label ShowreportedReasonlabel, Showreportedlabel;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    ArrayList<Report_enc> al;
    report r = new report();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        int report_count = 0;
        ResultSet rs1 = null;
        try {
            report_count = r.Reporteduser_no();
            rs1 = r.Reporteduser_name();
        } catch (SQLException ex) {
            Logger.getLogger(ShowReportedUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //showreporteduserspane.getChildren().addAll(reasonBox);
        /*stmt = db.conn.createStatement();
         String query = "SELECT COUNT(DISTINCT userName) FROM user join item on user.id = item.userID join report on item.id = report.itemID ";
         //String query = "SELECT COUNT(DISTINCT itemID) FROM report";
         ResultSet rs = stmt.executeQuery(query);
         String report_no = rs.getString(1);
         report_count = Integer.parseInt(report_no);
         System.out.println("" + report_count);*/
        /*stmt = db.conn.createStatement();
         String query1 = "SELECT DISTINCT userName FROM user join item on user.id = item.userID join report on item.id = report.itemID ";
         rs1 = stmt.executeQuery(query1);*/
            //fetch data from rs1
        al = new ArrayList<>(report_count);
        for (int counter = 0; counter < report_count; counter++) {
            try {
                rs1.next();
                al.add(new Report_enc(rs1.getString("userName")));
            } catch (SQLException ex) {
                Logger.getLogger(ShowReportedUsersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (Report_enc report : al) {
            showreporteduserspane.getChildren().add(report.getReport());
        }
    }

    public void ReasonPopup() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/aucation/FXML/reasonforreports.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.initStyle(StageStyle.UNDECORATED);
        s.setScene(new Scene(root1, 800, 500));
        s.show();
    }

    public void exit() {
        Stage e = (Stage) exit.getScene().getWindow();
        e.close();
    }
    public static String username;

    public void setUsername(String name) {
        username = name;
        //System.out.println("name set:" + username);
    }

    public String getUsername() {
        System.out.println("name get:" + username);
        return username;
    }
}

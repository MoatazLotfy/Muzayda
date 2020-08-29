/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.dataBase;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.text.resources.cldr.es.FormatData_es_419;

/**
 * FXML Controller class
 *
 * @author Moataz
 */
public class StatisticsController implements Initializable {

    public Button exit;
    public VBox biddersV;
    public HBox biddersH;
    Image image;
    public dataBase db = dataBase.getobj();
    Statement stmt = null;

    public void exit() {
        Stage e = (Stage) exit.getScene().getWindow();

        e.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        int item_count = 0;
        int item_count1 = 0;
        ResultSet rs1 = null;
        Label l = null;
        ResultSet rs = null;
        ResultSet rs4 = null;
        try {
            stmt = db.conn.createStatement();
            String query = "SELECT COUNT(distinct itemId) FROM normalBidders join item on normalBidders.itemID = item.id  where endDate < " + "'" + currentDate + "'";
            rs = stmt.executeQuery(query);
            String item_no = rs.getString(1);
            item_count = Integer.parseInt(item_no);
        } catch (SQLException ex) {
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                stmt = db.conn.createStatement();
                String query1 = "SELECT distinct item.Name,item.id,item.photoURL FROM normalBidders join item on normalBidders.itemID = item.id  where endDate < " + "'" + currentDate + "'";
                rs1 = stmt.executeQuery(query1);
            } catch (SQLException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        Path currentRelativePath = Paths.get("");
        for (int k = 0; k < item_count; k++) {

            biddersV = new VBox();
            biddersV.setStyle("-fx-background-color: rgba(0,0,0,0.6);");
            biddersV.setSpacing(10);
            ////////////////////////////
            //System.out.println("number of items:" + item_no);
            Label i = new Label();
            Label l2 = null;
            String pathString;       
            try {
                rs1.next();
                l = new Label(rs1.getString("Name"));
                pathString = currentRelativePath.toAbsolutePath().toString() + "\\src\\aucation\\ProductImages\\" + rs1.getString("photoURL");
                image = new Image("file:///" + pathString, 180, 180, true, true);
                i.setGraphic(new ImageView(image));
            } catch (SQLException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }

            l.setStyle("-fx-text-fill: #188c90;-fx-font-size: 14pt;-fx-min-width:200;-fx-background-color: rgba(0,0,0,0.4)");
            l.setAlignment(Pos.CENTER);
            biddersV.getChildren().addAll(i, l);
            try {
                stmt = db.conn.createStatement();
                System.out.println(rs1.getInt("id"));
                String query3 = "select COUNT(user.userName) from user join normalBidders on user.id = normalBidders.userID join item on normalBidders.itemID = item.id where normalBidders.itemID = " + rs1.getInt("id") + " and normalBidders.userID <> (select useriD from topBidders where topBidders.itemID = " + rs1.getInt("id") + ")and item.endDate < '"+currentDate+"'";
                ResultSet rs3 = stmt.executeQuery(query3);
                String item_no1 = rs3.getString(1);
                item_count1 = Integer.parseInt(item_no1);
                System.out.println(item_count1);
                stmt = db.conn.createStatement();
                String query4 = "select user.userName from user join normalBidders on user.id = normalBidders.userID join item on normalBidders.itemID = item.id where normalBidders.itemID = " + rs1.getInt("id") + " and normalBidders.userID <> (select useriD from topBidders where topBidders.itemID = " + rs1.getInt("id") + ")and item.endDate < '"+currentDate+"'";
                rs4 = stmt.executeQuery(query4);
            } catch (SQLException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int j = 0; j < item_count1; j++) {
                try {      
                    rs4.next();
                    l2 = new Label(rs4.getString("userName") + " : Lost!!");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                l2.setStyle("-fx-text-fill: red;-fx-font-size: 14pt;-fx-min-width:200");
                l2.setAlignment(Pos.CENTER);
                biddersV.getChildren().add(l2);
            }
            try {
            stmt = db.conn.createStatement();
            String query5 = "select userName from user join topBidders on user.id = topBidders.userId join item on item.id = topBidders.itemID where topBidders.itemID = " + rs1.getInt("id")+" and item.endDate < '"+currentDate+"'";
            ResultSet rs5 = stmt.executeQuery(query5);           
            Label l3 = new Label(rs5.getString("userName")+" : won");
            l3.setStyle("-fx-text-fill: green;-fx-font-size: 14pt;-fx-min-width:200");
            l3.setAlignment(Pos.CENTER);
            biddersV.getChildren().add(l3);
            biddersH.getChildren().add(biddersV);
            } catch (SQLException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }
    }

}


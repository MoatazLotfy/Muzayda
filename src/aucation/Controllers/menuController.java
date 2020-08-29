/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.Market_enc;
import aucation.Remove_user_enc;
import aucation.dataBase;
import aucation.person;
import aucation.user;
import static aucation.person.publicID;
import java.io.IOException;
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
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 *
 * @author Moataz
 */
public class menuController {

    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    person persn = new person();
    user usr = new user();
    public static String Actived_Button = new String();
    public String phone = persn.publicPhone, visa = persn.publicVisa, password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress, imagedescription, imagepath;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate = dateFormat.format(date);
//----------------
    public Button signout, myproducts, mail, market, Profile, Statistics, showreports, showfeedback, controlusers,CreateAdmin;
    public AnchorPane menulayout;
    public JFXHamburger hamb;
    Stage stage;
    Parent root;
    public Image image;
    FXMLLoader loader;
    int menustatus = 1;
    //static int item_index;
    public static int i_id;
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

    public void menuMove() {
        TranslateTransition t1 = new TranslateTransition();
        t1.setDuration(Duration.millis(200));
        t1.setNode(menulayout);

        if (menustatus == 1) {
            t1.setToX(300);
            t1.play();
            menustatus = 2;

            switch (Actived_Button) {
                case "Profile":
                    Profile.setStyle(" -fx-background-color: rgba(0,0,0,0.7)");
                    break;
                case "myproducts":
                    myproducts.setStyle(" -fx-background-color: rgba(0,0,0,0.7)");
                    break;
                case "mail":
                    mail.setStyle(" -fx-background-color: rgba(0,0,0,0.7)");
                    break;
                case "signout":
                    signout.setStyle(" -fx-background-color: rgba(0,0,0,0.7)");
                    break;
                case "market":
                    market.setStyle(" -fx-background-color: rgba(0,0,0,0.7)");
                    break;
                case "Statistics":
                    Statistics.setStyle(" -fx-background-color: rgba(0,0,0,0.7)");
                    break;
            }
        } else {
            t1.setToX(0);
            t1.play();
            menustatus = 1;
        }
    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void menuclick() {

        System.out.println(persn.publicType);
        if (persn.publicType.equals("Seller")) {
            market.setVisible(false);
            Statistics.setLayoutY(300);
            signout.setLayoutY(350);
            showreports.setVisible(false);
            showfeedback.setVisible(false);
            controlusers.setVisible(false);
            CreateAdmin.setVisible(false);
        }
        if (persn.publicType.equals("Admin")) {

            myproducts.setVisible(false);
            mail.setVisible(false);
            market.setVisible(false);
            Statistics.setVisible(false);
            //signout.setLayoutY(350);
        }
        if (persn.publicType.equals("Bidder")) {
            showreports.setVisible(false);
            showfeedback.setVisible(false);
            controlusers.setVisible(false);
            CreateAdmin.setVisible(false);
        }
        HamburgerBackArrowBasicTransition h = new HamburgerBackArrowBasicTransition(hamb);
        h.setRate(-1);
        h.setRate(h.getRate() * -1);
        h.play();
        menuMove();
        hamb.setOnMouseClicked(e -> {
            h.setRate(h.getRate() * -1);
            h.play();
            menuMove();
        });
    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/
    // menu buttons --------------
    public void Profileclick() {
        loadscene("/aucation/FXML/Profile.fxml", Profile);
        set_profile_data();   //call function that have user profile data.
        loadstage();
        Actived_Button = "Profile";
    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void myproductsclick() throws IOException, SQLException {

        loadscene("/aucation/FXML/MyProducts.fxml", myproducts);
        loadstage();
        Actived_Button = "myproducts";
    }

    public void mailclick() {
        Actived_Button = "mail";
        loadscene("/aucation/FXML/Mail.fxml", mail);
        loadstage();
    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/
    ArrayList<Market_enc> al_market;
     //need to move code to auction

    public void marketclick() throws SQLException {
        loadscene("/aucation/FXML/Market.fxml", market);

        MarketController c = (MarketController) loader.getController();
        ArrayList<String> item = new ArrayList<>();
        stmt = db.conn.createStatement();
        String query = "SELECT COUNT(*) FROM item where endDate > " + "'" + currentDate + "'";
        ResultSet rs = stmt.executeQuery(query);
        String item_no = rs.getString(1);
        int item_count = Integer.parseInt(item_no);
        System.out.println("number of items:" + item_no);

        if (item_count % 4 != 0 & item_count > 4) {
            item_count = item_count / 4 + 1;
        } else if (item_count % 4 == 0 & item_count > 4) {
            item_count = item_count / 4;
        } else {
            item_count = 1;
        }
        //------------------------------
        int colmun_count = 0;
        stmt = db.conn.createStatement();
        String query1 = "SELECT * FROM item where endDate > " + "'" + currentDate + "'";
        ResultSet rs1 = stmt.executeQuery(query1);
        System.out.println("count =" + item_count);
        al_market = new ArrayList<>(Integer.parseInt(item_no));
        int x = 0;
        for (int i = 0; i < Integer.parseInt(item_no); i++) {
            rs1.next();
            item.add(Integer.toString(rs1.getInt("id")));
            item.add(rs1.getString("Name"));
            al_market.add(new Market_enc(Integer.parseInt(item.get(x)), item.get(x + 1), rs1.getString("photoURL")));
            System.out.println("" + item.get(x) + item.get(x + 1));
            x = x + 2;
        }
        //act like two for loop the outer to the row of item inner the number of column in each row "max in row 4 item" 
        int q = 0;
        int w = 0;
        for (Market_enc market : al_market) {
            c.MGrid.add(market.getBox(), q, w);
            if (q == 3) {
                q = 0;
                w++;
            } else {
                q++;
            }
        }

        loadstage();
        Actived_Button = "market";

    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void ReportPopUp() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/aucation/FXML/ReportPopUp.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage s = new Stage();
            s.initModality(Modality.APPLICATION_MODAL);
            s.initStyle(StageStyle.UNDECORATED);
            s.setTitle("ABC");
            s.setScene(new Scene(root1, 800, 500));
            s.show();

        } catch (IOException ex) {
            Logger.getLogger(ReportPopUpController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void signoutclick() {
        loadscene("/aucation/FXML/HomeSCR.fxml", signout);
        loadstage();
        Actived_Button = "signout";

    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/
    public void set_profile_data() {
        ProfileController c = (ProfileController) loader.getController();
        c.ENameTXT.setText(persn.publicUserName);
        c.EPassTXT.setText(persn.publicPassword);
        c.EEmailTXT.setText(persn.publicEmail);
        c.EPhoneTXT.setText(persn.publicPhone);
        c.ECityTXT.setText(persn.publicAddress);
        c.ECreditTXT.setText(persn.publicVisa);
        System.out.println(publicID + persn.publicUserName + persn.publicPassword + persn.publicPhone + persn.publicVisa + persn.publicEmail + persn.publicAddress + persn.publicType);
    }

    public void statisticsclick() {

        loadscene("/aucation/FXML/Statistics.fxml", Statistics);
        loadstage();
        Actived_Button = "Statistics";

    }

    public void showreportsclick() {
        loadscene("/aucation/FXML/ShowReportedUsers.fxml", showreports);
        loadstage();
    }

    public void showfeedbackclick() {
        loadscene("/aucation/FXML/FeedbackView.fxml", showfeedback);
        loadstage();
    }

    public void ControlUsersclick() {
        loadscene("/aucation/FXML/ControlUser.fxml", controlusers);
        loadstage();
    }

    public void popUp() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/aucation/FXML/PDPopup.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage s = new Stage();
        s.initModality(Modality.APPLICATION_MODAL);
        s.initStyle(StageStyle.UNDECORATED);
        s.setTitle("ABC");
        s.setScene(new Scene(root1, 800, 500));
        s.show();

    }

    public void setItem_id(int set_id) {
        i_id = set_id;
    }

    public int getItem_id() {
        System.out.println("id:" + i_id);
        return i_id;
    }
    public void CreateAdminclick(){
        loadscene("/aucation/FXML/CreateAdmin.fxml", CreateAdmin);
        loadstage();
    }

    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/
    /**
     *
     * @author Moataz
     */
}

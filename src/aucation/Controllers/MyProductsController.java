/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.UserFactory;
import aucation.dataBase;
import aucation.person;
import aucation.user;
import aucation.userInterface;
import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 *
 * @author Moataz
 */
public class MyProductsController implements Initializable {

    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    person persn = new person();
    user usr = new user();
    public static String Actived_Button = new String();
    public String phone = persn.publicPhone, visa = persn.publicVisa, password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress, imagedescription, imagepath;
    //----------------
    public Button addproductBTN, exit, showproductBTN;
    public GridPane MPGrid;
    public Image image;
    public ImageView iv;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    menuController mc = new menuController();
    UserFactory factory = new UserFactory();

    @Override
    protected void finalize() throws Throwable {
        super.finalize(); //To change body of generated methods, choose Tools | Templates.
    }

    public MyProductsController() {
    }
    /*---------------------------------------------------------------------------------------------------------------------------------------------------
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

    public void addproductclick() {
        loadscene("/aucation/FXML/AddProduct.fxml", addproductBTN);
        loadstage();
    }

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate = dateFormat.format(date);

    public void showMyproducts() throws IOException, SQLException {
        VBox v;
        Label b;
        Label l;
        Label I;
        loadscene("/aucation/FXML/ShowMyProduct.fxml", showproductBTN);
        Path currentRelativePath = Paths.get("");
        ShowMyProductsController c = (ShowMyProductsController) loader.getController();
        int item_no = usr.MyProduct_no();
        int item_count = usr.ShowMyProduct_count();
        System.out.println(item_no + "," + item_count);
        if (item_no == 0) {
            System.out.println("no item");
        } else {
            //------------------------------
            int colmun_count = 0;
            ResultSet rs1 = usr.MyProduct();
            for (int i = 0; i < item_count; i++) {
                for (int j = 0; j < 4; j++) {
                    rs1.next();
                    I = new Label();
                    l = new Label(rs1.getString("Name"));
                    System.out.println(rs1.getString("Name"));
                    l.setStyle("-fx-text-fill: #188c90;-fx-font-size: 12pt;");
                    b = new Label("Sold");
                    b.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: green;-fx-font-size: 12pt;-fx-min-width:180;");
                    String pathString = currentRelativePath.toAbsolutePath().toString() + "\\src\\aucation\\ProductImages\\" + rs1.getString("photoURL");
                    image = new Image("file:///" + pathString, 180, 180, true, true);
                    System.out.println("file:///" + pathString);
                    I.setGraphic(new ImageView(image));
                    v = new VBox(I, l, b);
                    v.setStyle("-fx-background-color: rgba(0,0,0,0.5)");
                    c.SMPGrid.add(v, j, i);
                    colmun_count++;
                    if (colmun_count == item_no) {
                        break;
                    }
                }
            }
        }
        loadstage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox v;
        Button b;
        Label l;
        Label I;
        Path currentRelativePath = Paths.get("");
        int colmun_count = 0;
        int number_of_item = 0;
        try {
            number_of_item = usr.ShowMyProduct_no();
            if (number_of_item == 0) {
                System.out.println("no item");
            } else {
                int number_of_col = usr.ShowMyProduct_count();
                ResultSet rs3 = null;
                System.out.println(persn.publicType + " ggggggggggg " + rs3);
                if (person.publicType.equals("Bidder")) {
                    userInterface i = factory.getUser("Bidder");
                    rs3 = i.ShowMyProduct();
                } else if (person.publicType.equals("Seller")) {
                    userInterface i = factory.getUser("Seller");
                    rs3 = i.ShowMyProduct();
                }
                for (int i = 0; i < number_of_col; i++) {
                    for (int j = 0; j < 4; j++) {
                        rs3.next();
                        I = new Label();
                        System.out.println(rs3.getString("Name"));
                        l = new Label(rs3.getString("Name"));
                        l.setStyle("-fx-text-fill: #188c90;-fx-font-size: 12pt;");
                        b = new Button("Description");
                        b.setOnMouseClicked(e -> {

                            try {
                                mc.popUp();
                                System.out.println("aaaaaaaa");
                            } catch (IOException ex) {
                                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        b.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: #188c90;-fx-font-size: 12pt;-fx-min-width:180;");
                        String pathString = currentRelativePath.toAbsolutePath().toString() + "\\src\\aucation\\ProductImages\\" + rs3.getString("photoURL");
                        image = new Image("file:///" + pathString, 180, 180, true, true);
                        I.setGraphic(new ImageView(image));
                        v = new VBox(I, l, b);
                        v.setStyle("-fx-background-color: rgba(0,0,0,0.5)");
                        MPGrid.add(v, j, i);
                        colmun_count++;
                        if (colmun_count == number_of_item) {
                            break;
                        }
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(MyProductsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!persn.publicType.equals("Seller")) {
            addproductBTN.setVisible(false);
            showproductBTN.setLayoutX(420);
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation.Controllers;

import aucation.Seller;
import aucation.person;
import aucation.user;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

/**
 *
 * @author Moataz
 */

public class AddProductController implements Initializable{

    person persn = new person();
    user usr = new user();
    Seller seller = new Seller();
    public static String Actived_Button = new String();
    //public String phone = persn.publicPhone, visa = persn.publicVisa,password = persn.publicPassword, username = persn.publicUserName, email = persn.publicEmail, address = persn.publicAddress,imagedescription,imagepath;
    public String imagepath;
    //----------------
    public Button submitaddproduct, exit;
    public Label imageLBL;
    public TextField addproductname, addproductdescription, durationperdays, startprice;
    public VBox mailpane;
    public Image image;
    public ImageView iv;
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
    /*-----------------------------------------------------------------------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------------------------------------------------------------------------*/

    public void exit() {
        Stage e = (Stage) exit.getScene().getWindow();
        // do what you have to do
        e.close();
    }

    public void selectimageclick() throws IOException {
        FileChooser fc = new FileChooser();
        fc.setTitle("choose your photo");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("images", "*.jpg")
        );
        File f = fc.showOpenDialog(stage);
        imagepath = f.getName();
        Path currentRelativePath = Paths.get("");
        String pathString = currentRelativePath.toAbsolutePath().toString() + "\\src\\aucation\\ProductImages\\";  
        File v = new File(pathString + imagepath);

        Files.copy(f.toPath(), v.toPath());
        imageLBL.setText(imagepath);
    }


    public void add() throws IOException, SQLException {
        // MPGrid.getChildren().clear();
        //String a= addproductname.getText();
        //imagedescription = addproductdescription.getText();
        // هتحط فالداتا بيز a و b و  selectimageclick()
        //  //loadscene("FXML/MyProducts.fxml",submitaddproduct);
        //  loadstage();
        if(ValidateonSubmit(addproductname, addproductdescription,startprice,durationperdays)){
        seller.addItem(addproductname.getText(), "PhotoName", imagepath, addproductdescription.getText(), Integer.parseInt(startprice.getText()), Integer.parseInt(durationperdays.getText()));
        loadscene("/aucation/FXML/MyProducts.fxml", submitaddproduct);
        loadstage();
    }
        else {
             Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Invalid information");
                alert.showAndWait();   
                }
    }
 @Override
    public void initialize(URL location, ResourceBundle resources) {
         
            validateTextfiled(addproductdescription);
            validateTextfiled(startprice);
            validateTextfiled(addproductname);
            validateTextfiled(durationperdays);
    }
     
  /*Validation*/
               public void validateTextfiled(TextField t){
      t.focusedProperty().addListener((arg0, oldValue, newValue) -> {
        if (!newValue) { 
            if(t.getText().length()== 0){
               
                
                t.setStyle("-fx-border-color: transparent transparent red transparent;");
            }
            if(t.getText().length()!= 0){
               
                
                t.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
            }
            
        }

    });}
         public boolean ValidateonSubmit(TextField t1,TextField t2,TextField t3,TextField t4){
        if(t1.getText().equals("") || t2.getText().equals("") || t3.getText().equals("") || t4.getText().equals("")) {
                 return false;
        }
        else
            return true;
         }
}

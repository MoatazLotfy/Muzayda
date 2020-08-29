package aucation.Controllers;

import FXMLValidation.PhoneTextField;
import aucation.admin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAdminController implements Initializable {

    public Button createadminbutton;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    public TextField usernamecreateAdmin, emailcreateAdmin, passwordcreateAdmin;
    public PhoneTextField phonecreateAdmin;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
            validateEmail(emailcreateAdmin);
            validateTextfiled(usernamecreateAdmin);
            validateTextfiled(passwordcreateAdmin);
            validateTextfiled(phonecreateAdmin);
        createadminbutton.setOnMouseEntered(e -> createadminbutton.setStyle("-fx-text-fill:white;-fx-background-color:#31bfc4;"));
        createadminbutton.setOnMouseExited(e -> createadminbutton.setStyle("-fx-text-fill:#31bfc4;-fx-background-color:rgba(0,0,0,.7);"));
    }

    public void createadminbuttonclc() throws SQLException {
        if(ValidateonSubmit(usernamecreateAdmin, passwordcreateAdmin, phonecreateAdmin, emailcreateAdmin)){
        String userName = usernamecreateAdmin.getText();
        String email =emailcreateAdmin.getText();
        String password =passwordcreateAdmin.getText();
        String phone =phonecreateAdmin .getText();
        admin admn = new admin();
        admn.createAdmin(userName , phone , password , email);
        }
        else{
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Invalid information");
            alert.showAndWait();
        }
    }
     public void validateTextfiled(TextField t) {
        t.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (t.getText().length() == 0) {

                    t.setStyle("-fx-border-color: transparent transparent red transparent;");
                }
                if (t.getText().length() != 0) {

                    t.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
                }

            }

        });
    }

    public void validateEmail(TextField t) {
        t.focusedProperty().addListener((arg0, oldValue, newValue) -> {
            if (!newValue) {
                if (t.getText().length() == 0 || !t.getText().matches("[a-zA-Z]+[a-zA-Z0-9]*((\\_|\\.)[a-zA-Z0-9]*)?\\@[a-zA-Z]+\\.[a-zA-Z]+")) {

                    t.setStyle("-fx-border-color: transparent transparent red transparent;");
                } else {

                    t.setStyle("-fx-border-color: transparent transparent #188c90 transparent;");
                }

            }

        });
    }

    public boolean ValidateonSubmit(TextField t1, TextField t2, TextField t3, TextField t5) {
        if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || !t5.getText().matches("[a-zA-Z]+[a-zA-Z0-9]*((\\_|\\.)[a-zA-Z0-9]*)?\\@[a-zA-Z]+\\.[a-zA-Z]+")) {
            
            return false;
        } else {
            return true;
        }
    }

}

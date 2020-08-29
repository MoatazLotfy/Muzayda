package aucation.Controllers;

import aucation.person;
import aucation.dataBase;
import aucation.feedback;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FeedbackViewController implements Initializable {

    public Button exit;
    public VBox showfeedbak;
    public Label ShowreportedReasonlabel, Showreportedlabel;
    Stage stage;
    Parent root;
    FXMLLoader loader;
    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    person persn = new person();
    feedback FB = new feedback(null);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ResultSet r = FB.showFeedback();
            for (int i = 0; i < FB.feedback_no() ; i++) {

                HBox reasonBox = new HBox(8);
                Text reported = new Text();
                Label labReason = new Label();
                labReason.setStyle("-fx-text-fill: #31bfc4;-fx-min-width:350px;-fx-max-width:300px;-fx-min-height:0px;");
                r.next(); // to move to next row
                reported.setText(r.getString("message"));
                reasonBox.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-text-fill: #31bfc4;-fx-min-width:665;-fx-min-height:110px;-fx-max-width:665;");
                reported.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-fill: #31bfc4;-fx-min-width:665;-fx-min-height:110;");

                reasonBox.getChildren().addAll(reported, labReason);
                reasonBox.setAlignment(Pos.CENTER_RIGHT);
                showfeedbak.getChildren().addAll(reasonBox);

            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void exit() {
        Stage e = (Stage) exit.getScene().getWindow();
        e.close();
    }

}

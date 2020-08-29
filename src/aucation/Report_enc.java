package aucation;

import aucation.Controllers.ShowReportedUsersController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Report_enc {
    ShowReportedUsersController sruc = new ShowReportedUsersController();
    HBox reasonBox;
    Button reasonButton;
    Button Del;
    Text reported;
    Label labReason;

    public Report_enc(String name) {
        reasonBox = new HBox(8);
        reasonButton = new Button("Details");
        Del = new Button("Delete");
        reported = new Text();
        labReason = new Label();
        reported.setText(name);
        labReason.setStyle("-fx-alignment: CENTER;-fx-text-fill: #31bfc4;-fx-min-width:350px;-fx-max-width:350px;-fx-min-height:0px;");
        reasonBox.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-text-fill: #31bfc4;-fx-min-width:665px;-fx-min-height:110px;");
        reported.setStyle("-fx-background-color: rgba(0,0,0,0.4); -fx-fill: #31bfc4;-fx-min-width:665px;-fx-min-height:110px;");
        reasonButton.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-text-fill: #31bfc4;-fx-min-width:50px;-fx-min-height:30px;");
        Del.setStyle("-fx-background-color: rgba(0,0,0,0.8); -fx-text-fill: #31bfc4;-fx-min-width:50px;-fx-min-height:30px;");
        reasonBox.getChildren().addAll(reported, labReason);
        reasonBox.setAlignment(Pos.CENTER_RIGHT);
        
        reasonButton.setOnAction(e -> {
         
            try {
                sruc.setUsername(name);
                sruc.ReasonPopup();
                
            } catch (IOException ex) {
                Logger.getLogger(Report_enc.class.getName()).log(Level.SEVERE, null, ex);
            }
         });
        reasonBox.getChildren().addAll(reasonButton, Del);
    }

    public HBox getReport() {
        return reasonBox;
    }
    
}

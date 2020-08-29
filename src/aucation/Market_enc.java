package aucation;

import aucation.Controllers.FXMLDocumentController;
import aucation.Controllers.MyProductsController;
import aucation.Controllers.menuController;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Hesham Salama
 */
public class Market_enc {

    menuController mc = new menuController();
    VBox v;
    Button b;
    Label l;
    Label I;
    Button report;
    public Image image;
    int i_id;
    public Market_enc(int item_id,String u_name,String photo) {
        I = new Label();
        l = new Label(u_name);
        l.setStyle("-fx-text-fill: #188c90;-fx-font-size: 12pt;");
        l.setAlignment(Pos.CENTER);
        b = new Button("Description");
        report = new Button("! Report this user");
        b.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: #188c90;-fx-font-size: 12pt;-fx-min-width:180;");
        report.setStyle("-fx-background-color: rgba(0,0,0,0.5); -fx-text-fill: red;-fx-font-size: 12pt;-fx-min-width:180;");
         Path currentRelativePath = Paths.get("");
        String pathString = currentRelativePath.toAbsolutePath().toString() + "\\src\\aucation\\ProductImages\\"+photo;
        image = new Image("file:///"+pathString, 180, 180, true, true);

        I.setGraphic(new ImageView(image));
        v = new VBox(I, l, b, report);
        v.setStyle("-fx-background-color: rgba(0,0,0,0.5)");
        b.setOnAction(e -> {
            try {
                mc.setItem_id(item_id);
                mc.popUp();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        report.setOnAction(e -> {
            mc.setItem_id(item_id);
            mc.ReportPopUp();
            
        });

    }
 public VBox getBox() {
        return v;
    } 
 
 
}

package aucation;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Remove_user_enc {

    Label L;
    Button B;
    HBox H;
    public dataBase db = dataBase.getobj();
    Statement stmt = null;

    public Remove_user_enc(String u_name) {

        L = new Label(u_name);
        B = new Button("Remove");
        H = new HBox(L, B);
        H.setStyle(" -fx-min-width: 600;-fx-min-height: 50;-fx-max-width: 600;-fx-min-height: 50; -fx-background-color: rgba(0,0,0,0.6); ");
        L.setStyle("-fx-min-width: 450;-fx-min-height: 50;-fx-max-width: 450;-fx-min-height: 50; -fx-font-size: 20pt; -fx-text-fill: #188c90;");
        B.setStyle("-fx-min-width: 75;-fx-min-height: 50;-fx-max-width: 125;-fx-min-height: 50; -fx-font-size: 16pt; -fx-text-fill: #188c90;"
                + "-fx-background-color: rgba(0,0,0,0.4);  -fx-border-color: #188c90; -fx-background-radius: 20; -fx-border-radius: 20;");
        B.setOnAction(e -> {
            try {
                stmt = db.conn.createStatement();
                String query = "DELETE FROM user WHERE userName = " +"'"+ u_name+"'";
                stmt.executeUpdate(query);
            } catch (SQLException ex) {
                Logger.getLogger(Remove_user_enc.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public HBox getUser() {
        return H;
    }

}

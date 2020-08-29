package aucation;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class mail implements MailreadOnly{
    
    //public int mailId = 0;
    //public String mailHeader = "";
    //public String mailBody = "";

    public HashMap GetMails(int userid) {
        dataBase db = dataBase.getobj();
        HashMap hm = new HashMap();
        try {
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM mailBox where userID=" + userid + ";");
            while (rs.next()) {
                System.out.println("hey" + rs.getString("mailHeader"));
                hm.put(rs.getString("mailHeader"), rs.getString("mailBody"));

            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
        return hm;
    }

}

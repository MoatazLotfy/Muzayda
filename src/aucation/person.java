package aucation;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class person {

    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    public static int publicID;
    public static String publicVisa, publicPhone, publicUserName, publicType, publicEmail, publicAddress, publicPassword;
    /*Login Function*/

    public void login(String userName, String Pass) throws SQLException {

        String quote = "'";
        stmt = db.conn.createStatement();
        String query = "SELECT * FROM user where userName=" + quote + userName + quote;
        //System.out.println(query);
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            publicID = rs.getInt("id");
            publicUserName = rs.getString("userName");
            publicPassword = rs.getString("password");
            publicPhone = rs.getString("phone");
            publicVisa = rs.getString("visa");
            publicEmail = rs.getString("email");
            publicAddress = rs.getString("address");
            publicType = rs.getString("type");
   
        }
    }

    /*
     edit profile code take the input from the edit form and change it in data base via an unique email
     until using static id
     */
    public void editProfile(String name, String phone, String password, String visa, String email, String address) throws SQLException {
        if (db.checkeditProfile(name, phone, password, visa, email, address)) {
            System.out.println("Does !!");
        } else {
            System.out.println("Error!!");
        }
    }
}

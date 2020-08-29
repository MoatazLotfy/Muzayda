/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation;

import static aucation.person.publicID;
import static aucation.person.publicType;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Seller extends user implements userInterface {

    dataBase db = dataBase.getobj();
       /*add item uses the add function in the database*/

    public void addItem(String name, String photoName, String photoURL, String Description, int startPrice, int Duration) throws SQLException {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        String start = dateFormat.format(c.getTime());
        c.add(Calendar.DATE, Duration);
        String end = dateFormat.format(c.getTime());
        String[] colArr = {"Name", "startPrice", "description", "startDate", "endDate", "photoURL", "userID", "photoName"};
        String[] dataArr = {name, Integer.toString(startPrice), Description, start, end, photoURL, Integer.toString(publicID), photoName};
        db.addTodb(colArr, dataArr, "item");
    }

    @Override
    public ResultSet ShowMyProduct() {
        try {
            stmt = db.conn.createStatement();
            String query1 = null;
            ResultSet rs1 = null;
            query1 = "SELECT * FROM item where userID = " + publicID + " AND endDate > " + "'" + currentDate + "'";
            rs1 = stmt.executeQuery(query1);
            return rs1;
        } catch (SQLException ex) {
            Logger.getLogger(Seller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}

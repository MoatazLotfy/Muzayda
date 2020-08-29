/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aucation;

import static aucation.person.publicID;
import static aucation.person.publicType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bidder extends user implements userInterface {
    /*subscribeItem*/

    public void Subscribe(int itemid, int newprice) {

        try {
            stmt = db.conn.createStatement();
            stmt.executeUpdate("insert or replace into topBidders (userID,itemID,highPrice) values (" + publicID + "," + itemid + "," + newprice + ")");
            //db.conn.commit();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
    /*report function*/

    public void reportItem(report r, String itemName, String userName) {

    }

    @Override
    public ResultSet ShowMyProduct() {
        try {
            stmt = db.conn.createStatement();

            String query1 = null;
            ResultSet rs1 = null;
            query1 = "SELECT * FROM item join normalBidders on item.id = normalBidders.itemID WHERE normalBidders.userID = " + publicID
                    + " AND endDate > " + "'" + currentDate + "'";
            System.out.println(query1);
            rs1 = stmt.executeQuery(query1);

            return rs1;
        } catch (SQLException ex) {
        }
        return null;
    }

}

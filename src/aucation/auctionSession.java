package aucation;

import aucation.Controllers.menuController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class auctionSession {
    public dataBase db = dataBase.getobj();
    Statement stmt = null;
    public static HashMap GetDiscribtionandprice(int itemid) throws SQLException {
        HashMap hm = new HashMap();
        menuController m = new menuController();
        try {
            dataBase db = dataBase.getobj();
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT highPrice,description FROM  item join topBidders on item.id=topBidders.itemID where item.id=" + itemid + ";");
            hm.put(rs.getInt("highPrice"), rs.getString("description"));
        } catch (Exception e) {
            //System.err.println(e.getClass().getName() + ": " + e.getMessage());
            dataBase db = dataBase.getobj();
            Statement stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT startPrice,description FROM item where item.id=" + itemid);
            hm.put(rs.getInt("startPrice"), rs.getString("description"));
        }
        System.out.println("Operation done successfully");
        return hm;
    }

    public boolean checknormalbidder(int itemid, int userid) {
        
        try {
            stmt = db.conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM  normalBidders where itemID=" + itemid + " and userID=" + userid + ";");
            if (!rs.isBeforeFirst()) {
                rs.close();
                stmt.close();
                return false;
            }

            rs.close();
            stmt.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());

        }
        System.out.println("Operation done successfully");
        return true;
    }

    public void updatenormalbidder(int itemid, int newprice, int userid) {
        try {
            stmt = db.conn.createStatement();
            stmt.executeUpdate("UPDATE normalBidders SET price=" + newprice + " where itemID=" + itemid + " and userID=" + userid + ";");
            //db.conn.commit();
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

    public void insertnormal(int itemid, int price, int userid) {
        try {
            stmt = db.conn.createStatement();
            String sql = "INSERT INTO normalBidders (itemID,userID,price) "
                    + "VALUES (" + itemid + "," + userid + "," + price + " );";
            stmt.executeUpdate(sql);
            stmt.close();
            //db.conn.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");

    }
    
    /*announce Users*/
    public void announceUsers(int itemId){
        
    }
    
    /*get Winner*/
    public user getWinner(int itemId)
    {
        return null;//needs to use getter and setter
    }
    
    /*generate Invoice*/ // setter and getter
    public void generateInvoice(invoice i)
    {
        
    }
    /*send mail to the winner*/
    public void sendMail(mail m,int userid) // setter and getter
    {
        m.GetMails(userid);
    }
}
/*call the announceUsers and the sendmail after subscribe item to check the date*/
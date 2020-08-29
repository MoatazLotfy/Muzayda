package aucation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class user extends person {

    public int cardNumber = 0;
    item itemobj;
    MailreadOnly mailreadOnly;
    mail mailobj;
    dataBase db = dataBase.getobj();
    /*call add in the database*/
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();
    String currentDate = dateFormat.format(date);

    public void register(String name, String phone, String password, String visa, String email, String address, String Type) throws SQLException {
        publicUserName = name;
        publicPassword = password;
        publicPhone = phone;
        publicVisa = visa;
        publicEmail = email;
        publicAddress = address;
        publicType = Type;
        int id = 0;
        String[] colArr = {"userName", "password", "phone", "visa", "email", "address", "type"};
        String[] dataArr = {name, password, phone, visa, email, address, Type};
        id = db.addTodb(colArr, dataArr, "user");
        publicID = id;
        System.out.println("ID ====> " + publicID);

    }

    /*fill feedback*/
    public void fillFeedback(String feedback) throws SQLException {
        stmt = db.conn.createStatement();
        String query = "insert into feedback (message,senderID,Date) values (" + "'" + feedback + "'" + "," + publicID + ",01/01/2018);";
        //System.out.println(query);
        stmt.executeUpdate(query);
    }

    /*list all mails*/
    public void listMails(int id)// this is the static id of the current user
    {
        mailreadOnly.GetMails(id);
    }

    //number of items
    public int ShowMyProduct_no() throws SQLException {
        stmt = db.conn.createStatement();
        ResultSet rs = null;
        String item_no = null;
        if (publicType.equals("Bidder")) {
            String query = "SELECT COUNT(*) FROM normalBidders join item on normalBidders.itemID = item.id WHERE normalBidders.userID = " + publicID + " AND item.endDate > " + "'" + currentDate + "'";
            rs = stmt.executeQuery(query);
            item_no = rs.getString(1);
            //System.out.println("no:"+item_no);
        } else if (publicType.equals("Seller")) {
            String query = "SELECT COUNT(*) FROM item WHERE userID = " + publicID + " AND endDate > " + "'" + currentDate + "'";
            //System.out.println("query="+query);
            rs = stmt.executeQuery(query);
            item_no = rs.getString(1);
        }
        System.out.println(item_no);
        return Integer.parseInt(item_no);
    }

    //number of column
    public int ShowMyProduct_count() throws SQLException {

        int item_count = ShowMyProduct_no();
        if (item_count % 2 == 1 & item_count > 4) {
            item_count = item_count / 4 + 1;
        } else if (item_count % 2 == 0 & item_count > 4) {
            item_count = item_count / 4;
        } else {
            item_count = 1;
        }
        return item_count;

    }

    public int MyProduct_no() throws SQLException {
        stmt = db.conn.createStatement();
        ResultSet rs = null;
        String item_no = null;
        String query;
        System.out.println(publicType + " fffffffffffffff ");
        if (publicType.equals("Bidder")) {
            query = "SELECT COUNT(*) FROM topBidders join item on topBidders.itemID = item.id WHERE topBidders.userID = " + publicID + " AND item.endDate < " + "'" + currentDate + "'";
            rs = stmt.executeQuery(query);
            item_no = rs.getString(1);
            System.out.println(item_no);
        } else if (publicType.equals("Seller")) {
            query = "SELECT COUNT(*) FROM item WHERE userID = " + publicID + " AND endDate < " + "'" + currentDate + "'";
            //System.out.println("query="+query);
            rs = stmt.executeQuery(query);
            item_no = rs.getString(1);
        }
        return Integer.parseInt(item_no);
    }

    public ResultSet MyProduct() throws SQLException {
        stmt = db.conn.createStatement();
        String query1 = null;
        ResultSet rs1 = null;
        if (publicType.equals("Bidder")) {
            query1 = "SELECT * FROM item join topBidders on item.id = topBidders.itemID WHERE topBidders.userID = " + publicID
                    + " AND endDate < " + "'" + currentDate + "'";
            System.out.println(query1);
            rs1 = stmt.executeQuery(query1);
        } else if (publicType.equals("Seller")) {
            query1 = "SELECT * FROM item where userID = " + publicID + " AND endDate < " + "'" + currentDate + "'";
            rs1 = stmt.executeQuery(query1);
        }
        return rs1;

    }
}

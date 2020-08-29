package aucation;

import aucation.Controllers.ShowReportedUsersController;
import aucation.Controllers.menuController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class report {
    
    person persn = new person();
    menuController mc = new menuController();
    //ShowReportedUsersController src = new ShowReportedUsersController();
    public dataBase db = dataBase.getobj();
    Statement stmt = null;

    //get Reported user number "DISTINCT user"
    public int Reporteduser_no() throws SQLException {
        int report_count;
        stmt = db.conn.createStatement();
        String query = "SELECT COUNT(DISTINCT userName) FROM user join item on user.id = item.userID join report on item.id = report.itemID ";
        //String query = "SELECT COUNT(DISTINCT itemID) FROM report";
        ResultSet rs = stmt.executeQuery(query);
        String report_no = rs.getString(1);
        report_count = Integer.parseInt(report_no);
        System.out.println("" + report_count);
        return report_count;
    }

    //"name" of reported user 

    public ResultSet Reporteduser_name() throws SQLException {
        stmt = db.conn.createStatement();
        String query1 = "SELECT DISTINCT userName FROM user join item on user.id = item.userID join report on item.id = report.itemID ";
        ResultSet rs1 = stmt.executeQuery(query1);
        return rs1;
    }
    // number of label in details screen "content of report"
    public int ReasonOfReport_no(String user_name) throws SQLException {
        stmt = db.conn.createStatement();
        //System.out.println("the name:"+src.getUsername());
        String query = "SELECT COUNT(*) FROM report join item on report.itemID = item.id join user on user.id = item.userID where user.userName = " + "'" + user_name + "'";
        ResultSet rs = stmt.executeQuery(query);
        String r_no = rs.getString(1);
        int r_count = Integer.parseInt(r_no);
        System.out.println("count=" + r_count);
        return r_count;
    }
    //the content of report for each user in his items 
    public ResultSet selectReport(String user_name) throws SQLException {
        stmt = db.conn.createStatement();
        //System.out.println("the name:"+src.getUsername());
        String query1 = "SELECT reason FROM report join item on report.itemID = item.id join user on user.id = item.userID where user.userName = " + "'" + user_name + "'";
        ResultSet rs1 = stmt.executeQuery(query1);
        return rs1;
    }

    // add reason of report to database
    public void insertReport(String r_reason) {
        try {
            //problem here get the last one not the item i was click onit
            stmt = db.conn.createStatement();
            String query3 = "insert into report (itemID,reportedID,reason) values (" + mc.getItem_id() + "," + persn.publicID + "," + "'" + r_reason + "'" + ")";
            System.out.println("qury" + query3);
            stmt.executeUpdate(query3);
        } catch (SQLException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

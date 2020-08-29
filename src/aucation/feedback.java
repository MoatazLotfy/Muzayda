package aucation;

import static aucation.person.publicID;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//Immutable pattern "take data from constructor (constructor only set data)"

public final class feedback {

    //public int feedbackid = 0;
    //public String feedbackTitle = "";
    private final String feedbackDescription;
    dataBase db = dataBase.getobj();
    Statement stmt = null;
    /*Show feedback of the admin*/

    public feedback(String feedback_des) {
        feedbackDescription = feedback_des;
    }

    public ResultSet showFeedback() throws SQLException//retrive from feedback table
    {
        stmt = db.conn.createStatement();
        String query1 = "SELECT message FROM feedBack";
        ResultSet rs1 = stmt.executeQuery(query1);
        return rs1;
    }
    public int feedback_no() throws SQLException{
        stmt = db.conn.createStatement();
        String query = "SELECT COUNT(*) FROM feedBack";
        ResultSet rs = stmt.executeQuery(query);
        String feedback_no = rs.getString(1);
        int feedback_count = Integer.parseInt(feedback_no);
        return feedback_count;
    }

    public final void fillFeedback() throws SQLException {
        stmt = db.conn.createStatement();
        String query = "insert into feedback (message,senderID,Date) values (" + "'" + feedbackDescription + "'" + "," + publicID + ",01/01/2018);";
        stmt.executeUpdate(query);
    }

}

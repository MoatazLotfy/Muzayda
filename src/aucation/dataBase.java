package aucation;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dataBase {

    private static dataBase db;
    public static Connection conn = null;
    private Statement statement = null;
    private String cot = "'";

    private dataBase() {
        this.DBconnect();
    }

    public static dataBase getobj() {
        if (db == null) {
            db = new dataBase();
        }
        return db;
    }

    private Connection DBconnect() {
        try {
            // next 2 lines are used to connect the DB if connected return the connection else return NULL
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:final.db3");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }

    public boolean checkLogin(String username, String password) throws SQLException { // this is a demo function of login we must return id 
        String str1 = null;
        String pass = null;
        statement = conn.createStatement();
        String query = "SELECT userName,password FROM user where userName=" + cot + username + cot;
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            str1 = rs.getString("userName");
            pass = rs.getString("password");
        }
        if (str1 == null || pass == null) {
            return false;
        }
        return true;
    }

    public boolean checkeditProfile(String name, String phone, String password, String visa, String email, String address) throws SQLException {
        statement = conn.createStatement();
        String query = "UPDATE user SET email=" + cot + email + cot + ",password=" + cot + password + cot
                + ",phone=" + cot + phone + cot + ",visa=" + cot + visa + cot + ",address=" + cot + address + cot
                + "WHERE userName=" + cot + name + cot;
        if (statement.executeUpdate(query) == 0) {

            return false;
        }
        return true;

    }
    
    public boolean deleteUser()
    {
        return false;   
    }
    
    // if tableNum = 1 then add in USERS table
    // if tableNum = 2 then add in item table
      public int addTodb(String col[],String data[] , String tableName) throws SQLException
    {
        int id = 0 ;
        String query = null;
        String cols = "";
        String dats = "";
        for(int i = 0 ; i < col.length ; i++)
        {            
          if(i==col.length-1)
          {
              cols = cols + col[i];
          }else
          {
            cols = cols + col[i] + ",";  
          }          
        }
        for(int i = 0 ; i < data.length ; i++)
        {            
          if(i==data.length-1)
          {
              dats = dats + cot + data[i] + cot;
          }else
          {
              dats = dats + cot + data[i] + cot + ",";
          }          
        }
        query = "insert into " + tableName + "(" + cols + ")" + "values (" + dats + ")";
        System.out.println("query----->" + query);
        PreparedStatement statement = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
            id = generatedKeys.getInt(1);
        }   
    
         return id;
    }
}
    
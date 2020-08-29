package aucation;

import java.sql.SQLException;

public class admin extends person{
    report r ;
    feedback f;
    dataBase db = dataBase.getobj();
    /*create admain using add funtion of the database class*/
    public void createAdmin(String name, String phone, String password, String email) throws SQLException
    {
        String[] colArr = {"userName" , "password" , "phone" , "email" , "visa" , "address" , "type"};
        String[] dataArr = {name , password , phone , email , "null", "null" , "Admin"};
        db.addTodb(colArr, dataArr , "user");
    }
    /*delete user*/
    public void deleteUser(int id) // return the id of the user in the button id
    {
        
    }
    /*system Statistics with date*/
    public void systemStatistics() // return the id of the user in the button id
    {
        
    }
}

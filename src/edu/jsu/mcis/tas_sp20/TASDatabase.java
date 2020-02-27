package edu.jsu.mcis.tas_sp20;

import java.sql.*;

public class TASDatabase {
    
    Connection conn = null;
    PreparedStatement pstSelect = null, pstUpdate = null;
    ResultSet resultset = null;
    ResultSetMetaData metadata = null;
    
    public TASDatabase(){
        
        try{

        /* Identify the Server */
        
        String server = ("jdbc:mysql://localhost/tas");
        String username = "WarRoomC";
        String password = "CS488";
        System.out.println("Connecting to " + server + "...");

        /* Load the MySQL JDBC Driver */
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        
        /* Open Connection */

        conn = DriverManager.getConnection(server, username, password);
        
        /* Test Connection */

        if(conn.isValid(0))
            System.out.println("Connection Successful");
        }

        catch (Exception e) {
            System.err.println(e.toString());
        }
   }
    

    
    // creating objects 
    
    public Badge getBadge(String badgeid) {
        return null;
    }
    
    public Punch getPunch(int punchid){
        return null;
    }
    
    public Shift getShift(Badge badge){
        return null;
    }
    
    public Shift getShift (int shiftid){
        return null;
    }
    
}


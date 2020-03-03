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

            if(conn.isValid(0)){
            System.out.println("Connection Successful");
            }
    
            
        }
        
        catch (Exception e) {
            System.err.println(e.toString());
        }
        
        /* Close Other Database Objects */
        
        finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; } catch (Exception e) {} }
            
        }
    }
        
    
    
    // creating objects 
    
        public Badge getBadge(String badgeid){
        try{
            
        // prepare statement
        pstSelect = conn.prepareStatement("SELECT * FROM badge WHERE id = ?");
        
        //set params
        pstSelect.setString(1, badgeid);
        
        //execute
        pstSelect.execute();
        resultset = pstSelect.getResultSet();
        
        //get results
        resultset.first();
        String idNum = resultset.getString(1);
        String name = resultset.getString(2);
        
        Badge b = new Badge(name, idNum);
        
        return b;
        }
        
        catch(Exception e){
            System.err.println("** getBadge: " + e.toString());
        }

        return null;
        
    }
    
    public Punch getPunch(int punchid){
        
        try{
            
        // prepare statement
        pstSelect = conn.prepareStatement("SELECT * FROM punch WHERE id = ?");
        
        //set params
        pstSelect.setInt(1, punchid);
        
        //execute
        pstSelect.execute();
        resultset = pstSelect.getResultSet();
        
        //get results
        resultset.first();
        String idNum = resultset.getString(1);
        String terminalId = resultset.getString(2);
        String badgeId = resultset.getString(3);
        String originalTimeStamp = resultset.getString(4);
        String punchTypeId = resultset.getString(5);
        
        Punch p = new Punch(idNum, terminalId, badgeId, originalTimeStamp, punchTypeId);
        
        return p;
        }
        
        catch(Exception e){
            System.err.println("** getPunch: " + e.toString());
        }

        return null;
    }
    
    public Shift getShift(Badge badge){
        
        try{
            
        // prepare statement
        pstSelect = conn.prepareStatement("SELECT shift.id, shift.description,"
                + " shift.start, shift.stop shift.interval, shift.graceperiod,"
                + " shift.dock, shift.lunchstart, shift.lunchstop, shift.lunchdeduct"
                + " FROM shift INNER JOIN employee ON employee.shiftid = shift.id"
                + " INNER JOIN badge ON badge.id = employee.badgeid WHERE badge.id"
                + " = ?");
        
        //set params
        pstSelect.setString(1, badge.getID() );
        
        //execute
        pstSelect.execute();
        resultset = pstSelect.getResultSet();
        
        //get results
        resultset.first();
        String idNum = resultset.getString(1);
        String description = resultset.getString(2);
        String start = resultset.getString(3);
        String stop = resultset.getString(4);
        String interval = resultset.getString(5);
        String gracePeriod = resultset.getString(6);
        String dock = resultset.getString(7);
        String lunchStart = resultset.getString(8);
        String lunchStop = resultset.getString(9);
        String lunchDeduct = resultset.getString(10);
        
        Shift s = new Shift(idNum,description, start, stop, interval, gracePeriod,
                dock, lunchStart, lunchStop, lunchDeduct);
        
        return s;
        }
        
        catch(Exception e){
            System.err.println("** getShift: " + e.toString());
        }

        return null;
    }
    
    public Shift getShift (int shiftid){
        
        try{
            
        // prepare statement
        pstSelect = conn.prepareStatement("SELECT * FROM shift WHERE id = ?");
        
        //set params
        pstSelect.setInt(1, shiftid);
        
        //execute
        pstSelect.execute();
        resultset = pstSelect.getResultSet();
        
        //get results
        resultset.first();
        String idNum = resultset.getString(1);
        String description = resultset.getString(2);
        String start = resultset.getString(3);
        String stop = resultset.getString(4);
        String interval = resultset.getString(5);
        String gracePeriod = resultset.getString(6);
        String dock = resultset.getString(7);
        String lunchStart = resultset.getString(8);
        String lunchStop = resultset.getString(9);
        String lunchDeduct = resultset.getString(10);
        
        Shift s = new Shift(idNum,description, start, stop, interval, gracePeriod,
                dock, lunchStart, lunchStop, lunchDeduct);
        
        return s;
        }
        
        catch(Exception e){
            System.err.println("** getShift: " + e.toString());
        }

        return null;
    }
    
    public void close() {
        
        try{
            
            conn.close();
        }
        catch(Exception e){
            System.err.println("** close: " + e.toString());
        }
    }
}

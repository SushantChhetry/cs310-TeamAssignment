package edu.jsu.mcis.tas_sp20;

import java.sql.*;

public class TASDatabase {
    
    Connection conn = null;
    Statement stmt = null;
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
    
    public Badge getBadge(String badgeid) throws SQLException {
        
        /* Execute Query */
            
        System.out.println("Creating statement...");
        stmt = conn.createStatement();

        String sql = "SELECT badge.description FROM badge WHERE id = '" 
                + badgeid + "'";
        resultset = stmt.executeQuery(sql);
        
        return (Badge)resultset;
    }
    
    public Punch getPunch(int punchid) throws SQLException{
        
        /* Execute Query */
            
        System.out.println("Creating statement...");
        stmt = conn.createStatement();

        String sql = "SELECT punch.terminalid, punch.badgeid, punch.originaltimestamp,"
                + " punch.punchtypeid FROM punch WHERE id = '" + punchid + "'";
        resultset = stmt.executeQuery(sql);
        
        return (Punch)resultset;
    }
    
    public Shift getShift(Badge badge) throws SQLException{
        
        /* Execute Query */
            
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        

        String sql = "SELECT shift.id, shift.description, shift.start, shift.stop,"
                + " shift.interval, shift.graceperiod, shift.dock, shift.lunchstart,"
                + " shift.lunchstop, shift.lunchdeduct FROM shift "
                + " INNER JOIN employee ON employee.shiftid = shift.id"
                + " INNER JOIN badge ON badge.id = employee.badgeid"
                + " WHERE badge.id = '" + badge.getID() + "'";
        resultset = stmt.executeQuery(sql);
        
        return (Shift)resultset;
    }
    
    public Shift getShift (int shiftid) throws SQLException{
        
        /* Execute Query */
            
        System.out.println("Creating statement...");
        stmt = conn.createStatement();

        String sql = "SELECT shift.description, shift.start, shift.stop, shift.interval,"
                + " shift.graceperiod, shift.dock, shift.lunchstart, shift.lunchstop,"
                + " shift.lunchdeduct FROM shift WHERE id = '" + shiftid + "'";
        resultset = stmt.executeQuery(sql);
        
        return (Shift)resultset;
    }
    
    public void close() throws SQLException {
        conn.close();
    }
}

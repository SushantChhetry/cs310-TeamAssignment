package edu.jsu.mcis.tas_sp20;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

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
            resultset.first();

            //get results            resultset.first();

            String idNum = resultset.getString(1);
            String name = resultset.getString(2);

            Badge b = new Badge(idNum, name);

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
        int punchID = resultset.getInt(1);
        int punchTerminalID = resultset.getInt(2);
        Badge punchBadge = new Badge(resultset.getString(3), "Description");
        int punchTypeID = resultset.getInt(5);
        
        Punch p = new Punch(punchBadge, punchTerminalID, punchTypeID);
        
        // prepare statement to get timestamp
        pstSelect = conn.prepareStatement("SELECT *, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS ts FROM punch WHERE id = ?");
        
        //set params
        pstSelect.setInt(1, punchid);
        
        //execute
        pstSelect.execute();
        resultset = pstSelect.getResultSet();
        
        //get results
        resultset.first();
        long punchOriginalTimestamp = resultset.getLong("ts");
        
        p.setOriginalTimestamp(punchOriginalTimestamp);
        
        
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
            pstSelect = conn.prepareStatement("SELECT * FROM shift INNER JOIN employee"
                    + " ON employee.shiftid = shift.id INNER JOIN badge ON badge.id"
                    + " = employee.badgeid WHERE badge.id = ?");

            //set params
            pstSelect.setString(1, badge.getID() );

            //execute
            pstSelect.execute();
            resultset = pstSelect.getResultSet();

            //get results
            resultset.first();

            String description = resultset.getString("description");

            String start = resultset.getString("start");
            String[] arrayStart = start.split(":");
            int startHour = Integer.parseInt(arrayStart[0]);
            int startMin = Integer.parseInt(arrayStart[1]);

            String stop = resultset.getString("stop");
            String[] arrayStop = stop.split(":");
            int stopHour = Integer.parseInt(arrayStop[0]);
            int stopMin = Integer.parseInt(arrayStop[1]);

            int interval = resultset.getInt("interval");
            int gracePeriod = resultset.getInt("graceperiod");
            int dock = resultset.getInt("dock");

            String lunchStart = resultset.getString("lunchstart");
            String[] arrayLunchStart = lunchStart.split(":");
            int lunchStartHour = Integer.parseInt(arrayLunchStart[0]);
            int lunchStartMin = Integer.parseInt(arrayLunchStart[1]);

            String lunchStop = resultset.getString("lunchstop");
            String[] arrayLunchStop = lunchStop.split(":");
            int lunchStopHour = Integer.parseInt(arrayLunchStop[0]);
            int lunchStopMin = Integer.parseInt(arrayLunchStop[1]);

            int lunchDeduct = resultset.getInt("lunchdeduct");

            Shift s = new Shift(description, startHour, startMin, stopHour, stopMin, gracePeriod, interval,
                    dock, lunchStartHour, lunchStartMin, lunchStopHour, lunchStopMin, lunchDeduct);

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

            String description = resultset.getString("description");

            String start = resultset.getString("start");
            String[] arrayStart = start.split(":");
            int startHour = Integer.parseInt(arrayStart[0]);
            int startMin = Integer.parseInt(arrayStart[1]);

            String stop = resultset.getString("stop");
            String[] arrayStop = stop.split(":");
            int stopHour = Integer.parseInt(arrayStop[0]);
            int stopMin = Integer.parseInt(arrayStop[1]);

            int interval = resultset.getInt("interval");
            int gracePeriod = resultset.getInt("graceperiod");
            int dock = resultset.getInt("dock");

            String lunchStart = resultset.getString("lunchstart");
            String[] arrayLunchStart = lunchStart.split(":");
            int lunchStartHour = Integer.parseInt(arrayLunchStart[0]);
            int lunchStartMin = Integer.parseInt(arrayLunchStart[1]);

            String lunchStop = resultset.getString("lunchstop");
            String[] arrayLunchStop = lunchStop.split(":");
            int lunchStopHour = Integer.parseInt(arrayLunchStop[0]);
            int lunchStopMin = Integer.parseInt(arrayLunchStop[1]);

            int lunchDeduct = resultset.getInt("lunchdeduct");

            Shift s = new Shift(description, startHour, startMin, stopHour, stopMin, gracePeriod, interval,
                    dock, lunchStartHour, lunchStartMin, lunchStopHour, lunchStopMin, lunchDeduct);

            return s;
            
        }
                
        catch(Exception e){
            System.err.println("** getShift: " + e.toString());
        }

        return null;
    }
    
    public ArrayList<Punch> getDailyPunchList(Badge badge, long ts){
        
        ArrayList<Punch> list = new ArrayList<Punch>();
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(ts);
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = format1.format(calendar.getTime());
        calendar.add(Calendar.DATE,1);
        String date1 = format1.format(calendar.getTime());
        
        try{
            
            //preparing Select query
            pstSelect = conn.prepareStatement("SELECT * FROM shift WHERE badgeid = ?");
            
            //executing Select query
            pstSelect.execute();
            resultset = pstSelect.getResultSet();
            
            //getting result
            resultset.first();
            
            
        }
        
        catch (Exception e) {
            
            System.err.println(e.toString());
            
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

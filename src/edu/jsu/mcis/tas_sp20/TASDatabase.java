package edu.jsu.mcis.tas_sp20;

import java.sql.*;
import java.sql.Connection;
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
    
    boolean hasresults;
    int columnCount = 0;
    
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
        
        Timestamp originalTimeStamp = null;
        Punch p = null;
        
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
            String punchBadge = resultset.getString(3);
            originalTimeStamp = resultset.getTimestamp(4);
            int punchTypeID = resultset.getInt(5);

            p = new Punch(punchID, punchTerminalID, punchBadge,
                    originalTimeStamp, punchTypeID);
            
        }
        
        catch(Exception e){
            System.err.println("** getPunch: " + e.toString());
        }

        return p;
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
    
    public int insertPunch(Punch p) {   
        
        String badgeID = p.getBadgeid();
        int terminalID = p.getTerminalid();
        int punchTypeID = p.getPunchtypeid();
        int punchId = 1;
        
        GregorianCalendar ots = new GregorianCalendar();
        ots.setTimeInMillis(p.getOriginaltimestamp());
        String originaltimestamp = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(ots.getTime());
        
        PreparedStatement pst;
        String query;
        int result = 0;
        ResultSet resultSet;
        
        try {
     
            query = "INSERT INTO punch (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES (?, ?, ?, ?)";
  
            pst = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, terminalID);
            pst.setString(2, badgeID);
            pst.setString(3, originaltimestamp);
            pst.setInt(4, punchTypeID);
            
                        
            pst.execute();
            resultSet = pst.getGeneratedKeys();
            resultSet.first();
            return resultSet.getInt(1);
   
        }
        catch (Exception e) {
            System.err.println("** insertPunch: " + e.toString());
        }
        
        return punchId;
    }
    
    public ArrayList<Punch> getDailyPunchList(Badge badge, long ts){
        
        ArrayList<Punch> list = new ArrayList<Punch>();
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(ts);
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = format1.format(calendar.getTime());
        calendar.add(Calendar.DATE,1);
        String date1 = format1.format(calendar.getTime());
        int lastPunchType = 0;
        
        try{
            
            //preparing Select query
           
            pstSelect = conn.prepareStatement("SELECT id,terminalid,badgeid,originaltimestamp,"
                    + "punchtypeid FROM tas.punch WHERE badgeid = '"
                    + badge.getID() + "' AND originaltimestamp LIKE '%"
                    + date + "%'");
            
            //executing Select query
            hasresults = pstSelect.execute();                
            resultset = pstSelect.getResultSet();
            metadata = resultset.getMetaData();
            columnCount = metadata.getColumnCount(); 
            
            //getting result
            System.out.println("Getting Results...");
            resultset = pstSelect.getResultSet();                    
                                      
            for(int i = 1; i < columnCount; i++) {

                if (resultset.isLast()) {
                    lastPunchType = resultset.getInt(5);
                    break;  

                }

                resultset.next();                       
                list.add(new Punch(resultset.getInt("id")
                        ,resultset.getInt("terminalid"),resultset.getString("badgeid")
                        ,resultset.getTimestamp("originaltimestamp")
                        ,resultset.getInt("punchtypeid")));

            }
            
            
        }
        
        catch (Exception e) {
            
            System.err.println(e.toString());
            
        }
        
        /*Closing other database objects*/
        
        finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; 
            } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; 
            } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; 
            } catch (Exception e) {} }
            
        }
        
        try {
        
            /* Preparing Select Query */
          
            pstSelect = conn.prepareStatement("SELECT id,terminalid,badgeid,originaltimestamp,"
                    + "punchtypeid FROM tas.punch WHERE badgeid = '"
                    + badge.getID() + "' AND originaltimestamp LIKE '%"
                    + date1 + "%'");
                
            /* Executing Select Query */
            
            hasresults = pstSelect.execute();                
            resultset = pstSelect.getResultSet();
            metadata = resultset.getMetaData();
            columnCount = metadata.getColumnCount(); 
            
            /* Getting Results */
   
            System.out.println("Getting Results ...");
            
                    /* Getting ResultSet */
                        
                    resultset = pstSelect.getResultSet();                    
                                      
                    for(int i = 1; i < columnCount; i++) {
                        
                        if (resultset.isLast() ) {
                            
                            break;  
                            
                        }
                        
                        resultset.next(); 
                        
                        if (resultset.getInt(5) == TASLogic.CLOCKOUT && lastPunchType == TASLogic.CLOCKIN) {
                                                 
                        list.add(new Punch(resultset.getInt(1)
                                ,resultset.getInt(2),resultset.getString(3)
                                ,resultset.getTimestamp(4)
                                ,resultset.getInt(5)));
                        
                        }
                        
                    }
        }        
        
        catch (Exception e) {
            
            System.err.println(e.toString());
            
        }
        
        /* Closeing other Database Objects */
        
        finally {
            
            if (resultset != null) { try { resultset.close(); resultset = null; 
            } catch (Exception e) {} }
            
            if (pstSelect != null) { try { pstSelect.close(); pstSelect = null; 
            } catch (Exception e) {} }
            
            if (pstUpdate != null) { try { pstUpdate.close(); pstUpdate = null; 
            } catch (Exception e) {} }
            
        }
        
        return list;
           
        
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

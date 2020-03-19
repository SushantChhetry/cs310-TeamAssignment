package edu.jsu.mcis.tas_sp20;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class Punch {
    
    private String punchBadge;
    private int punchID;
    private int punchTerminalID;
    private int punchTypeID;
    private long originalTimeStamp;
    private String adjustmenttype;
    private String adjustedTimestamp;
        
    
    public Punch (int punchID, int punchTerminalID, String punchBadge,
            Timestamp originalTimeStamp, int punchTypeID) {
    
        if(punchID >= 0){this.punchID = punchID;}
        this.punchTerminalID = punchTerminalID;
        this.punchBadge = punchBadge;
        this.originalTimeStamp = originalTimeStamp.getTime();
        this.punchTypeID = punchTypeID;
        
    }
    
    
    public Punch (Badge badge, int pTerminalID, int pTypeID){
        this.punchBadge = badge.getID();
        this.punchID = 0;
        this.punchTerminalID = pTerminalID;
        this.punchTypeID = pTypeID;
        this.originalTimeStamp = 0;
        this.adjustedTimestamp = null;
    }

    
    public String getBadgeid() {
        return punchBadge;
    }
    
    public void setBadge(String newBadge) {
        this.punchBadge = newBadge;
    }
    
    public int getPunchID() {
        return punchID;
    }

    public void setPunchID(int punchID) {
        this.punchID = punchID;
    }

    public int getTerminalid() {
        return punchTerminalID;
    }

    public void setPunchTerminalID(int punchTerminalID) {
        this.punchTerminalID = punchTerminalID;
    }

    public int getPunchtypeid() {
        return punchTypeID;
    }

    public void setPunchTypeID(int punchTypeID) {
        this.punchTypeID = punchTypeID;
    }

    public long getOriginaltimestamp() {
        return originalTimeStamp;
    }

    public void setOriginalTimeStamp(long newOriginalTimeStamp) {
        this.originalTimeStamp = newOriginalTimeStamp;
    }
    
    public String printOriginalTimestamp(){
        StringBuilder sb = new StringBuilder();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(originalTimeStamp);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");
        
        sb.append("#").append(punchBadge);

        switch (punchTypeID) {
            case 0:
                sb.append(" CLOCKED OUT: ");
                break;
            case 1:
                sb.append(" CLOCKED IN: ");
                break;
            case 2:
                sb.append(" TIMED OUT: ");
                break;
            default:
                sb.append(" ERROR ");
                break;
        }
        
        String ots = sdf.format(gc.getTime());
        sb.append(ots);

        return (sb.toString().toUpperCase());
    }
    
    public String getAdjustedTimestamp() {
        return adjustedTimestamp;
    }
    
    public void setAdjustedTimestamp(String adjustedTimestamp) {
        this.adjustedTimestamp = adjustedTimestamp;
    }
    
    public String printAdjustedTimestamp() {
        return null;
    }
    
    public void adjust (Shift s)
    {
        
    }
    
    public Date grace (long shiftStart, long shiftStop)
    {
        GregorianCalendar graced = new GregorianCalendar();
        graced.setTimeInMillis(originalTimeStamp);
        
        int pt = punchTypeID;
        long start = shiftStart;
        long stop = shiftStop;
        
        if ( pt <= 0){
            graced.setTimeInMillis(stop);
        }
        if ( pt >= 1){
            graced.setTimeInMillis(start);
        }
        
        adjustmenttype = ("Shift Grace");
        
        return graced.getTime();
    }
    public Date dock (long shiftStart, long shiftStop)
    {
        GregorianCalendar docked = new GregorianCalendar();
        docked.setTimeInMillis(originalTimeStamp);
        
        int pt = punchTypeID;
        long start = shiftStart;
        long stop = shiftStop;
        
        if ( pt <= 0){
            
            docked.setTimeInMillis(stop);
            docked.add(Calendar.MINUTE, -15);
        }
        if ( pt >= 1){
            
            docked.setTimeInMillis(start);
            docked.add(Calendar.MINUTE, 15);
        }
        
        docked.set(Calendar.SECOND, 0);
        
        adjustmenttype = ("Shift Dock");
        
        return docked.getTime();
    }
    public Date intervalRound ()
    {
        GregorianCalendar rounded = new GregorianCalendar();
        rounded.setTimeInMillis(originalTimeStamp);
        
        int pt = punchTypeID;
        int minute = rounded.get(Calendar.MINUTE);
        int adjusted = 0;
        
        if ( pt <= 0){
            adjusted = roundDown(minute, 15);
            rounded.set(Calendar.MINUTE, adjusted);
        }
        if ( pt >= 1){
            
            adjusted = roundUp(minute, 15);
            rounded.set(Calendar.MINUTE, adjusted);
        }
        
        rounded.set(Calendar.SECOND, 0);
        
        adjustmenttype = ("Interval Round");
        
        return rounded.getTime();
    }
    public Date none ()
    {
        GregorianCalendar noned = new GregorianCalendar();
        noned.setTimeInMillis(originalTimeStamp);
        
        noned.set(Calendar.SECOND, 0); 
        
        adjustmenttype = ("None");
            
        return noned.getTime();
    }
    
    int roundDown(int n, int m) 
    {
        return n >= 0 ? (n / m) * m : ((n - m + 1) / m) * m;
    }
    
    int roundUp(int n, int m) 
    {
        return n >= 0 ? ((n + m - 1) / m) * m : (n / m) * m;
    }
            
}

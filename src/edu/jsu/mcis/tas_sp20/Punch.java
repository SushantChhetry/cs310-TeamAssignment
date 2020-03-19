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
            
}

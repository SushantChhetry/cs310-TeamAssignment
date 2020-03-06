package edu.jsu.mcis.tas_sp20;

import java.text.SimpleDateFormat;
import java.util.*;

public class Punch {
    
    private Badge punchBadge;
    private int punchID;
    private int punchTerminalID;
    private int punchTypeID;
    private long originalTimestamp;
    private String adjustmenttype;
    private String adjustedTimestamp;
    
    
    public Punch (Badge badge, int pTerminalID, int pTypeID){
        this.punchBadge = badge;
        this.punchID = 0;
        this.punchTerminalID = pTerminalID;
        this.punchTypeID = pTypeID;
        this.originalTimestamp = 0;
        this.adjustedTimestamp = null;
    }

    
    public Badge getBadge() {
        return punchBadge;
    }
    
    public void setBadge(Badge newBadge) {
        this.punchBadge = newBadge;
    }
    
    public int getPunchID() {
        return punchID;
    }

    public void setPunchID(int punchID) {
        this.punchID = punchID;
    }

    public int getPunchTerminalID() {
        return punchTerminalID;
    }

    public void setPunchTerminalID(int punchTerminalID) {
        this.punchTerminalID = punchTerminalID;
    }

    public int getPunchTypeID() {
        return punchTypeID;
    }

    public void setPunchTypeID(int punchTypeID) {
        this.punchTypeID = punchTypeID;
    }

    public long getOriginalTimestamp() {
        return originalTimestamp;
    }

    public void setOriginalTimestamp(long newOriginalTimestamp) {
        this.originalTimestamp = newOriginalTimestamp;
    }
    
    public String printOriginalTimestamp(){
        StringBuilder sb = new StringBuilder();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(originalTimestamp);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");
        
        sb.append("#").append(punchBadge.getID());

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

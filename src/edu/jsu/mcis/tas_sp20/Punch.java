package edu.jsu.mcis.tas_sp20;

import java.util.*;


public class Punch {
    
    private Badge punchBadge;
    private int punchID;
    private int punchTerminalID;
    private int punchTypeID;
    private long originalTimestamp;
    private String adjustmenttype;
    private String adjustedTimestamp;
    
    
    public Punch (Badge badge, int pTerminalID, int pTypeID, long timestamp){
        this.punchBadge = badge;
        this.punchID = 0;
        this.punchTerminalID = pTerminalID;
        this.punchTypeID = pTypeID;
        this.originalTimestamp = timestamp;
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
        
        sb.append(punchBadge.getID());
        switch (punchID) {
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
        
        int day = gc.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                sb.append("SUN ");
                break;
            case 2:
                sb.append("MON ");
                break;
            case 3:
                sb.append("TUE ");
                break;
            case 4:
                sb.append("WED ");
                break;
            case 5:
                sb.append("THU ");
                break;
            case 6:
                sb.append("FRI ");
                break;
            case 7:
                sb.append("SAT ");
                break;
            default:
                sb.append("ERR ");
                break;
        }
        
        sb.append(gc.get(Calendar.MONTH)).append("/");
        sb.append(gc.get(Calendar.DAY_OF_MONTH)).append("/");
        sb.append(gc.get(Calendar.YEAR)).append(" ");
        
        sb.append(gc.get(Calendar.HOUR_OF_DAY)).append(":");
        sb.append(gc.get(Calendar.MINUTE)).append(":");
        sb.append(gc.get(Calendar.SECOND));
        
        
        return (sb.toString());
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

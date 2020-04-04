package edu.jsu.mcis.tas_sp20;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
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
        this.originalTimeStamp = System.currentTimeMillis();
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
    
    public String adjustTimestamp(GregorianCalendar gc, String adjustmentnote)
    {
        StringBuilder sb = new StringBuilder();
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
        
        String ots = sdf.format(gc.getTime()).toUpperCase();
        sb.append(ots);
        sb.append(" ").append(adjustmentnote);

        return (sb.toString());
    }
    
    public void setAdjustedTimestamp(String adjustedTimestamp) {
        this.adjustedTimestamp = adjustedTimestamp;
    }
    
    public String printAdjustedTimestamp() {
        return adjustedTimestamp;
    }
    
    public void adjust (Shift s)
    {
        GregorianCalendar p = new GregorianCalendar();
        p.setTimeInMillis(originalTimeStamp);
        LocalTime ltP = LocalTime.of(p.get(Calendar.HOUR_OF_DAY), p.get(Calendar.MINUTE), p.get(Calendar.SECOND));
        
        LocalTime shiftStart = s.getShiftStart();
        LocalTime shiftStop = s.getShiftStop();
        long interval = s.getShiftInterval();
        LocalTime lunchStart = s.getShiftLunchStart();
        LocalTime lunchStop = s.getShiftLunchStop();
        
        System.out.println(ltP.toString());
        
        switch (punchTypeID) {
            // Clock Out //
            case 0:
                if (p.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || p.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                {
                    p.set(Calendar.SECOND, 0);
                    
                    adjustmenttype = ("(Interval Round)");
                    setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                }
                else if (ltP.isAfter(shiftStop.minusSeconds(1)))
                {
                    if (ltP.isBefore(shiftStop.plusMinutes(interval).plusSeconds(1)))
                    {
                        p.set(Calendar.HOUR_OF_DAY, s.getShiftStopHour());
                        p.set(Calendar.MINUTE, s.getShiftStopMin());
                        p.set(Calendar.SECOND, 0);
                    
                        adjustmenttype = ("(Shift Stop)");
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }
                    
                    else
                    {
                        adjustmenttype = ("(WIP-0-Interval Round)");
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }
                } 
                else if (ltP.isBefore(lunchStop.plusSeconds(1)) && ltP.isAfter(lunchStart.minusSeconds(1)))
                {
                    p.set(Calendar.HOUR_OF_DAY, s.getLunchStartHour());
                    p.set(Calendar.MINUTE, s.getLunchStartMin());
                    p.set(Calendar.SECOND, 0);
                        
                    adjustmenttype = ("(Lunch Start)");
                    setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                }
                else if (ltP.isBefore(shiftStop))
                {
                    if (ltP.isAfter(shiftStop.minusMinutes(s.getShiftGrace()).minusSeconds(1)))
                    {
                        p.set(Calendar.HOUR_OF_DAY, s.getShiftStopHour());
                        p.set(Calendar.MINUTE, s.getShiftStopMin());
                        p.set(Calendar.SECOND, 0);
                    
                        adjustmenttype = ("(Shift Stop)"); // Grace Period
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }
                    else
                    {
                        p.set(Calendar.HOUR_OF_DAY, s.getShiftStopHour());
                        p.set(Calendar.MINUTE, s.getShiftStopMin());
                        p.add(Calendar.MINUTE, -(s.getShiftDock()));
                        p.set(Calendar.SECOND, 0);
                    
                        adjustmenttype = ("(Shift Dock)");
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }
                }
                break;
                
            // Clock In //
            case 1:
                if (p.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || p.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                {
                    p.set(Calendar.SECOND, 0);
                    
                    adjustmenttype = ("(Interval Round)");
                    setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                }
                else if (ltP.isBefore(shiftStart.plusSeconds(1)))
                {
                    if (ltP.isAfter(shiftStart.minusMinutes(interval).minusSeconds(1)))
                    {
                        p.set(Calendar.HOUR_OF_DAY, s.getShiftStartHour());
                        p.set(Calendar.MINUTE, s.getShiftStartMin());
                        p.set(Calendar.SECOND, 0);

                        adjustmenttype = ("(Shift Start)");
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }
                    else
                    {
                        adjustmenttype = ("(WIP-1-Interval Round)");
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }    
                }
                else if (ltP.isBefore(lunchStop.plusSeconds(1)) && ltP.isAfter(lunchStart.minusSeconds(1)))
                {
                    p.set(Calendar.HOUR_OF_DAY, s.getLunchStopHour());
                    p.set(Calendar.MINUTE, s.getLunchStopMin());
                    p.set(Calendar.SECOND, 0);
                        
                    adjustmenttype = ("(Lunch Stop)");
                    setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                }
                else if (ltP.isAfter(shiftStart))
                {
                    if (ltP.isBefore(shiftStart.plusMinutes(s.getShiftGrace()).plusSeconds(1)))
                    {
                        p.set(Calendar.HOUR_OF_DAY, s.getShiftStartHour());
                        p.set(Calendar.MINUTE, s.getShiftStartMin());
                        p.set(Calendar.SECOND, 0);
                    
                        adjustmenttype = ("(Shift Start)"); // Grace Period
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }
                    else
                    {
                        p.set(Calendar.HOUR_OF_DAY, s.getShiftStartHour());
                        p.set(Calendar.MINUTE, s.getShiftStartMin());
                        p.add(Calendar.MINUTE, s.getShiftDock());
                        p.set(Calendar.SECOND, 0);
                    
                        adjustmenttype = ("(Shift Dock)");
                        setAdjustedTimestamp(adjustTimestamp(p, adjustmenttype));
                    }
                }
                break;
        }
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
        
        adjustmenttype = ("(Shift Grace)");
        
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
        
        adjustmenttype = ("(Shift Dock)");
        
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
        
        adjustmenttype = ("(Interval Round)");
        
        return rounded.getTime();
    }
    public Date none ()
    {
        GregorianCalendar noned = new GregorianCalendar();
        noned.setTimeInMillis(originalTimeStamp);
        
        noned.set(Calendar.SECOND, 0); 
        
        adjustmenttype = ("(None)");
            
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

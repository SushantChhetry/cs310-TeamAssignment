
package edu.jsu.mcis.tas_sp20;

import java.time.LocalTime;

public class Shift {
    
    private String shiftID;
    private String shiftDescription;
    private LocalTime shiftStart;
    private LocalTime shiftStop;
    private int shiftGrace;
    private int shiftInterval;
    private int shiftDock;
    private LocalTime shiftLunchStart;
    private LocalTime shiftLunchStop;
    private int shiftLunchDeduct;
    private LocalTime shiftLunchDuration;
    
    public Shift(String sID, String sDescrip, LocalTime sStart, LocalTime sStop, 
            int sGrace, int sInterval, int sDock, LocalTime sLunchStart,
            LocalTime sLunchStop, int sLunchDeduct){
        
        this.shiftID = sID;
        this.shiftDescription = sDescrip;
        this.shiftStart = sStart;
        this.shiftStop = sStop;
        this.shiftGrace = sGrace;
        this.shiftInterval = sInterval;
        this.shiftDock = sDock;
        this.shiftLunchStart = sLunchStart;
        this.shiftLunchStop = sLunchStop;
        this.shiftLunchDeduct = sLunchDeduct;
        
    }
    
    // getter and setter methods
    
    public String getShiftID() {
        return shiftID;
    }

    public void setShiftID(String shiftID) {
        this.shiftID = shiftID;
    }

    public String getShiftDescription() {
        return shiftDescription;
    }

    public void setShiftDescription(String shiftDescription) {
        this.shiftDescription = shiftDescription;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(LocalTime shiftStart) {
        this.shiftStart = shiftStart;
    }

    public LocalTime getShiftStop() {
        return shiftStop;
    }

    public void setShiftStop(LocalTime shiftStop) {
        this.shiftStop = shiftStop;
    }

    public int getShiftGrace() {
        return shiftGrace;
    }

    public void setShiftGrace(int shiftGrace) {
        this.shiftGrace = shiftGrace;
    }

    public int getShiftInterval() {
        return shiftInterval;
    }

    public void setShiftInterval(int shiftInterval) {
        this.shiftInterval = shiftInterval;
    }

    public int getShiftDock() {
        return shiftDock;
    }

    public void setShiftDock(int shiftDock) {
        this.shiftDock = shiftDock;
    }

    public LocalTime getShiftLunchStart() {
        return shiftLunchStart;
    }

    public void setShiftLunchStart(LocalTime shiftLunchStart) {
        this.shiftLunchStart = shiftLunchStart;
    }

    public LocalTime getShiftLunchStop() {
        return shiftLunchStop;
    }

    public void setShiftLunchStop(LocalTime shiftLunchStop) {
        this.shiftLunchStop = shiftLunchStop;
    }

    public int getShiftLunchDeduct() {
        return shiftLunchDeduct;
    }

    public void setShiftLunchDeduct(int shiftLunchDeduct) {
        this.shiftLunchDeduct = shiftLunchDeduct;
    }

    public LocalTime getShiftLunchDuration() {
        return shiftLunchDuration;
    }
    
    // for Lunch duration (LunchStart - LunchStop)
    
    public LocalTime getDuration(){
        return null;
    }
 
}


package edu.jsu.mcis.tas_sp20;

import java.time.LocalTime;

public class Shift {
    
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
    
    public Shift(String sDescrip, int sStartHour, int sStartMin, 
            int sStopHour, int sStopMin, int sGrace, int sInterval, int sDock, 
            int sLunchStartHour,int sLunchStartMin, int sLunchStopHour, 
            int sLunchStopMin, int sLunchDeduct){
        
        this.shiftDescription = sDescrip;
        this.shiftStart = LocalTime.of(sStartHour, sStartMin);
        this.shiftStop = LocalTime.of(sStopHour, sStopMin);
        this.shiftGrace = sGrace;
        this.shiftInterval = sInterval;
        this.shiftDock = sDock;
        this.shiftLunchStart = LocalTime.of(sLunchStartHour, sLunchStartMin);
        this.shiftLunchStop = LocalTime.of(sLunchStopHour, sLunchStopMin);
        this.shiftLunchDeduct = sLunchDeduct;
    }
    
    // getter methods

    public LocalTime getShiftStart() {
        return shiftStart;
    }
    
    public int getShiftStartHour(){
        return getShiftStart().getHour();
    }
    
    public int getShiftStartMin(){
        return getShiftStart().getMinute();
    }
        
    public LocalTime getShiftStop() {
        return shiftStop;
    }
    public int getShiftStopHour(){
        return getShiftStop().getHour();
    }
    
    public int getShiftStopMin(){
        return getShiftStop().getMinute();
    }
    
    public int getShiftGrace() {
        return shiftGrace;
    }

    public int getShiftInterval() {
        return shiftInterval;
    }
    
    public int getShiftDock() {
        return shiftDock;
    }
    
    public LocalTime getShiftLunchStart() {
        return shiftLunchStart;
    }
    
    public int getLunchStartHour(){
        return getShiftLunchStart().getHour();
    }
    
    public int getLunchStartMin(){
        return getShiftLunchStart().getMinute();
    }
    
    public LocalTime getShiftLunchStop() {
        return shiftLunchStop;
    }
    
    public int getLunchStopHour(){
        return getShiftLunchStop().getHour();
    }
    
    public int getLunchStopMin(){
        return getShiftLunchStop().getMinute();
    }
    
    public int getShiftLunchDeduct() {
        return shiftLunchDeduct;
    }

    //setter methods;
    
    public void setShiftStart(LocalTime shiftStart) {
        this.shiftStart = shiftStart;
    }

    public void setShiftStop(LocalTime shiftStop) {
        this.shiftStop = shiftStop;
    }


    public void setShiftGrace(int shiftGrace) {
        this.shiftGrace = shiftGrace;
    }

    public void setShiftInterval(int shiftInterval) {
        this.shiftInterval = shiftInterval;
    }

    public void setShiftDock(int shiftDock) {
        this.shiftDock = shiftDock;
    }

    public void setShiftLunchStart(LocalTime shiftLunchStart) {
        this.shiftLunchStart = shiftLunchStart;
    }

    public void setShiftLunchStop(LocalTime shiftLunchStop) {
        this.shiftLunchStop = shiftLunchStop;
    }

    public void setShiftLunchDeduct(int shiftLunchDeduct) {
        this.shiftLunchDeduct = shiftLunchDeduct;
    }

    public LocalTime getShiftLunchDuration() {
        return shiftLunchDuration;
    }

    public void setShiftLunchDuration(LocalTime shiftLunchDuration) {
        this.shiftLunchDuration = shiftLunchDuration;
    }
    
    // for total shift hours 
    public int totalShiftDuration(){
        int totalShiftStartTime = (getShiftStart().getHour()*60) + 
                getShiftStart().getMinute();
        
        int totalShiftStopTime = (getShiftStop().getHour()*60) + 
                getShiftStop().getMinute();
        
        return (totalShiftStopTime - totalShiftStartTime);
    }
    
    // for Lunch duration (LunchStart - LunchStop)
    
    public int totalLunchDuration(){
        int totalLunchStartTime = (getShiftLunchStart().getHour()*60) +
                getShiftLunchStart().getMinute();
        
        int totalLunchStopTime = (getShiftLunchStop().getHour()*60) +
                getShiftLunchStop().getMinute();
        
        return (totalLunchStopTime - totalLunchStartTime);
    }
    
    @Override
    public String toString(){
        
        StringBuilder sb = new StringBuilder();
        sb.append(shiftDescription + ": ").append(shiftStart.toString())
                .append(" - ");
        sb.append(shiftStop.toString()).append(" (")
                .append(totalShiftDuration());
        sb.append(" minutes); Lunch: ").append(shiftLunchStart.toString())
                .append(" - ");
        sb.append(shiftLunchStop.toString()).append(" (")
                .append(totalLunchDuration()).append(" minutes)");
        
        return sb.toString();
    }
    
}

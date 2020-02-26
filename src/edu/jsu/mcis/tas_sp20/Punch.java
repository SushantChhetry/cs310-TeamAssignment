
package edu.jsu.mcis.tas_sp20;


public class Punch {
    
    private int punchID;
    private String punchTerminalID;
    private int punchBadgeID;
    private int punchTypeID;
    private int printOriginalTimestamp;
    
    public Punch (int pID, String pTerminalID, int pBadgeID, int pTypeID){
        this.punchID = pID;
        this.punchTerminalID = pTerminalID;
        this.punchBadgeID = pBadgeID;
        this.punchTypeID = pTypeID;
    }

    public int getPunchID() {
        return punchID;
    }

    public void setPunchID(int punchID) {
        this.punchID = punchID;
    }

    public String getPunchTerminalID() {
        return punchTerminalID;
    }

    public void setPunchTerminalID(String punchTerminalID) {
        this.punchTerminalID = punchTerminalID;
    }

    public int getPunchBadgeID() {
        return punchBadgeID;
    }

    public void setPunchBadgeID(int punchBadgeID) {
        this.punchBadgeID = punchBadgeID;
    }

    public int getPunchTypeID() {
        return punchTypeID;
    }

    public void setPunchTypeID(int punchTypeID) {
        this.punchTypeID = punchTypeID;
    }

    public int getPrintOriginalTimestamp() {
        return printOriginalTimestamp;
    }

    public void setPrintOriginalTimestamp(int printOriginalTimestamp) {
        this.printOriginalTimestamp = printOriginalTimestamp;
    }
    
    public String printOriginalTimestamp(){
        return null;
    }
            
}

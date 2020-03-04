
package edu.jsu.mcis.tas_sp20;


public class Punch {
    
    private int punchID;
    private String punchTerminalID;
    private String punchBadgeID;
    private int punchTypeID;
    private int printOriginalTimestamp;
    
    public Punch (int pID, String pTerminalID, String pBadgeID, int pTypeID, int ptOriginalTimestamp){
        this.punchID = pID;
        this.punchTerminalID = pTerminalID;
        this.punchBadgeID = pBadgeID;
        this.punchTypeID = pTypeID;
        this.printOriginalTimestamp = ptOriginalTimestamp;
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

    public String getPunchBadgeID() {
        return punchBadgeID;
    }

    public void setPunchBadgeID(String punchBadgeID) {
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

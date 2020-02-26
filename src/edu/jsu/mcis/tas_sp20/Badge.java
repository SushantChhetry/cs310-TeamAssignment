
package edu.jsu.mcis.tas_sp20;


public class Badge {
    
    private String badgeID;
    private String badgeDescription;
    
    //creating constructor
    
    public Badge(String bID, String badgeDescrip){
        
        this.badgeID = bID;
        this.badgeDescription = badgeDescrip;
        
    }
    
    //creating getter and setter method

    public String getBadgeID() {
        return badgeID;
    }

    public void setBadgeID(String badgeID) {
        this.badgeID = badgeID;
    }

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        this.badgeDescription = badgeDescription;
    }
       
}

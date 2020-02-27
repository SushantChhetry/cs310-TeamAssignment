
package edu.jsu.mcis.tas_sp20;


public class Badge {
    
    private String badgeID;
    private String badgeDescription;
    
    //creating constructor
    
    public Badge(String bID, String badgeDescrip){
        
        this.badgeID = bID;
        this.badgeDescription = badgeDescrip;
        
    }
    
    public String getDescription(){
        return badgeDescription;
    }
    
    public String getID(){
        return badgeID;
    }
    
    @Override
    public String toString(){
        String string = "";
        StringBuilder sb = new StringBuilder();
        sb.append("#");
        sb.append(this.getID() + " " + "(");
        sb.append(this.getDescription() + ")");
        string = sb.toString();

        return string;
    }   
}

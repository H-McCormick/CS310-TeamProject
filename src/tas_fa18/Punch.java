/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tas_fa18;

/**
 *
 * @author forresthood
 */
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

public class Punch {
    private int id;
    private int termID;
    private String badgeID;
    private GregorianCalendar originalStamp;
    private GregorianCalendar adjustedStamp;
    private int punchTypeID;
    
    public static final int CLOCKED_OUT = 0; 
    public static final int CLOCKED_IN = 1;
    public static final int TIMED_OUT = 2;

    public Punch() {
            this.id = 0;
            this.termID = 0;
            this.badgeID = null;
            this.originalStamp = new GregorianCalendar();
            this.adjustedStamp = null;
            this.punchTypeID = 0;

    }

    public Punch(int id, int terminalid, String badgeid, GregorianCalendar originalTimestamp, int punchtypeid){
        //todo badge class stuff
        this.id = id;
        this.badgeID = badgeid;
        this.termID = terminalid;
        this.punchTypeID = punchtypeid;
        this.originalStamp = originalTimestamp;
        this.adjustedStamp = null;
        
    }
    
    public int getID(){
        return this.id;
    }
    public void setID(int newID){
        this.id = newID;
    }
    
    public int getTermId(){
        return this.termID;
    }
    public void setTermID(int newTerm){
        this.termID = newTerm;
    }
    
    public String getBadgeID(){
        return this.badgeID;
    }
    public void setBadgeID(String newbadge){
        this.badgeID = newbadge;
    }
    
    public GregorianCalendar getOriginalStamp(){
        return this.originalStamp;
    }
    public void setOriginalStamp(GregorianCalendar newOrigin){
        this.originalStamp = newOrigin;
    }
    
    public GregorianCalendar getAdjustedStamp(){
        return this.adjustedStamp;
    }
    public void setAdjustedStamp(GregorianCalendar newAdjust){
        this.adjustedStamp = newAdjust;
    }
    
    public int getPunchTypeID(){
        return this.punchTypeID;
    }
    public void setPunchTypeID(int punchtype){
        this.punchTypeID = punchtype;
    }
    
    /*IMPORTANT*/
    //Snellen Note: Use SimpleDateFormat instead (http://tutorials.jenkov.com/java-internationalization/simpledateformat.html)
    
    public String printOriginalTimestamp(){
        String origin = "#";
        origin = origin.concat(this.badgeID);
        switch (this.punchTypeID) {
            case 0:
                origin = origin.concat(" CLOCKED OUT: ");
                break;
            case 1:
                origin = origin.concat(" CLOCKED IN: ");
                break;
            case 2:
                origin = origin.concat(" TIMED OUT: ");
                break;
            default:
                break;
        }
        SimpleDateFormat form = new SimpleDateFormat("EEE");
        String dayFormatted = form.format(this.originalStamp.getTime());
        dayFormatted = dayFormatted.toUpperCase();
        origin = origin.concat(dayFormatted);
        origin = origin.concat(" ");
        
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateFormatted = fmt.format(this.originalStamp.getTime());
        origin = origin.concat(dateFormatted);
        
        
        
        return origin;
    }
    
    public String printFormattedOriginalTimestamp(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(this.originalStamp.getTime());
    }


}
//2018-08-01 05:49:24
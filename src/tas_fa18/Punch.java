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

    public Punch(Badge badge, int terminalid, int punchtypeid){
        //todo badge class stuff
        this.id = 0;
        this.badgeID = badge.getID();
        this.termID = terminalid;
        this.punchTypeID = punchtypeid;
        this.originalStamp = new GregorianCalendar();
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
        int day = this.originalStamp.get(GregorianCalendar.DAY_OF_WEEK);
        switch (day) {
            case 2:
                origin = origin.concat("MON ");
                break;
            case 3:
                origin = origin.concat("TUE ");
                break;
            case 4:
                origin = origin.concat("WED ");
                break;
            case 5:
                origin = origin.concat("THU ");
                break;
            case 6:
                origin = origin.concat("FRI ");
                break;
            case 7:
                origin = origin.concat("SAT ");
                break;
            case 1:
                origin = origin.concat("SUN ");
                break;
            default:
                System.out.println("Error parsing day");
                break;
        }
        int month = this.originalStamp.get(GregorianCalendar.MONTH);
        switch (month){
            case 1:
                origin = origin.concat("01/");
                break;
            case 2:
                origin = origin.concat("02/");
                break;
            case 3:
                origin = origin.concat("03/");
                break;
            case 4:
                origin = origin.concat("04/");
                break;
            case 5:
                origin = origin.concat("05/");
                break;
            case 6:
                origin = origin.concat("06/");
                break;
            case 7:
                origin = origin.concat("07/");
                break;
            case 8:
                origin = origin.concat("08/");
                break;
            case 9:
                origin = origin.concat("09/");
                break;
            case 10:
                origin = origin.concat("10/");
                break;
            case 11:
                origin = origin.concat("11/");
                break;
            case 12:
                origin = origin.concat("12/");
                break;
            default:
                System.out.println("Error parsin month");
                break;
            
        }
        int dayN = this.originalStamp.get(GregorianCalendar.DATE);
        if (dayN < 10){
            origin = origin.concat("0");
            origin = origin.concat(Integer.toString(dayN));
            origin = origin.concat("/");
        }
        else{
            origin = origin.concat(Integer.toString(dayN));
            origin = origin.concat("/");
        }
        
        int year = this.originalStamp.get(GregorianCalendar.YEAR);
        origin = origin.concat(Integer.toString(year));
        origin = origin.concat(" ");
        
        int hour = this.originalStamp.get(GregorianCalendar.HOUR_OF_DAY);
        if (hour < 10){
            origin = origin.concat("0");
            origin = origin.concat(Integer.toString(hour));
            origin = origin.concat(":");
        }
        else{
            origin = origin.concat(Integer.toString(hour));
            origin = origin.concat(":");
        }
        
        int minute = this.originalStamp.get(GregorianCalendar.MINUTE);
        if(minute < 10){
            origin = origin.concat("0");
            origin = origin.concat(Integer.toString(minute));
            origin = origin.concat(":");
        }
        else{
            origin = origin.concat(Integer.toString(minute));
            origin = origin.concat(":");
        }
        
        int second = this.originalStamp.get(GregorianCalendar.SECOND);
        if (second < 10){
            origin = origin.concat("0");
            origin = origin.concat(Integer.toString(second));
        }
        else{
            origin = origin.concat(Integer.toString(second));
        }
        
        
        
        return origin;
    }

}

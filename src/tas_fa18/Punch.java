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
    private String adjustedRule;
    
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
    
    public Punch(Badge badge, int termid, int punchtypeid){
        this.id = 0;
        this.termID = termid;
        this.badgeID = badge.getID();
        this.originalStamp = new GregorianCalendar();
        this. adjustedStamp = null;
        this.punchTypeID = punchtypeid;
        
    }

    public Punch(int id, int terminalid, String badgeid, GregorianCalendar originalTimestamp, int punchtypeid){
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
    
    public int getTerminalid(){
        return this.termID;
    }
    public void setTerminalid(int newTerm){
        this.termID = newTerm;
    }
    
    public String getBadgeid(){
        return this.badgeID;
    }
    public void setBadgeid(String newbadge){
        this.badgeID = newbadge;
    }
    
    public long getOriginaltimestamp(){
        return this.originalStamp.getTimeInMillis();
    }
    public void setOriginaltimestamp(GregorianCalendar newOrigin){
        this.originalStamp = newOrigin;
    }
    
    public long getAdjustedtimestamp(){
        return this.adjustedStamp.getTimeInMillis();
    }
    public void setAdjustedtimestamp(GregorianCalendar newAdjust){
        this.adjustedStamp = newAdjust;
    }
    
    public int getPunchtypeid(){
        return this.punchTypeID;
    }
    public void setPunchtypeid(int punchtype){
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
    public String printFormattedAdjustedTimestamp(){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(this.adjustedStamp.getTime());
    }
    
    //Feature 3
    public void adjust(Shift shift){
        long shiftStart = shift.getStart().getTimeInMillis();
        long shiftStop = shift.getStop().getTimeInMillis();
        int gracePeriod = shift.getGracePeriod();
        int interval = shift.getInterval();
        int dock = shift.getDock();
        long lunchStart = shift.getLunchStart().getTimeInMillis();
        long lunchStop = shift.getLunchStop().getTimeInMillis();
        long punchin = this.originalStamp.getTimeInMillis();
        
        long gracePeriodMillis = gracePeriod*60000;
        long intervalMillis = interval*60000;
        long dockMillis = dock*60000;
        
        
        //If it's a clock in
        if(this.punchTypeID == CLOCKED_IN){
            if
            
            
        }
        
    }
    


}

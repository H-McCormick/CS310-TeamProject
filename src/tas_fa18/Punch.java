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
import java.util.Calendar;
import java.util.Set;


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
            this.adjustedStamp = new GregorianCalendar();
            this.punchTypeID = 0;

    }
    
    public Punch(Badge badge, int termid, int punchtypeid){
        this.id = 0;
        this.termID = termid;
        this.badgeID = badge.getID();
        this.originalStamp = new GregorianCalendar();
        this. adjustedStamp = new GregorianCalendar();
        this.punchTypeID = punchtypeid;
        
    }

    public Punch(int id, int terminalid, String badgeid, GregorianCalendar originalTimestamp, int punchtypeid){
        this.id = id;
        this.badgeID = badgeid;
        this.termID = terminalid;
        this.punchTypeID = punchtypeid;
        this.originalStamp = originalTimestamp;
        this.adjustedStamp = new GregorianCalendar();
        
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
    public String printAdjustedTimestamp(){
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
        String dayFormatted = form.format(this.adjustedStamp.getTime());
        dayFormatted = dayFormatted.toUpperCase();
        origin = origin.concat(dayFormatted);
        origin = origin.concat(" ");
        
        SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String dateFormatted = fmt.format(this.adjustedStamp.getTime());
        origin = origin.concat(dateFormatted);
        
        origin = origin.concat(" (");
        origin = origin.concat(this.adjustedRule);
        origin = origin.concat(")");
        
        
        
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
    
    private void noneRule(int interval){
        //Round up to adjusted timeframe (15)
                 
            //Minutes in the hour
            int min = this.originalStamp.get(Calendar.MINUTE);
                 
            //Check moving up
            boolean adjustUp = min % interval > interval/2;
                 
            //Adjustment amount 
            int adjustAmount = (min%interval);
                         
            //If it is being rounded up
            if(adjustUp){
                this.adjustedStamp.add(Calendar.MINUTE, (interval - adjustAmount));
            }
            //If it is being rounded down
            else{
                this.adjustedStamp.add(Calendar.MINUTE, - adjustAmount);
            }
                     
            this.adjustedStamp.set(Calendar.SECOND, 0);
            this.adjustedRule = "Interval Round";
    }
    
    //Feature 3
    public void adjust(Shift shift){
        this.adjustedStamp.setTimeInMillis(this.originalStamp.getTimeInMillis());
        GregorianCalendar shiftstart2 = new GregorianCalendar();
        
        long shiftStart = shift.getStart().getTimeInMillis();
        shiftstart2.setTimeInMillis(shiftStart);
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
        
        int day = originalStamp.get(Calendar.DAY_OF_WEEK);
        
        if(day == 7 || day == 1){
            this.noneRule(interval);
        }
        
        
        //If it's a clock in
        else if(this.punchTypeID == CLOCKED_IN){
            //Shift Start
            
            if(((shiftStart + gracePeriod) < punchin) && (shiftStart + intervalMillis) > punchin){
                //After grace period
                this.adjustedStamp.setTimeInMillis(shiftStart + intervalMillis);
                this.adjustedRule = "Shift Dock";
            }
            else if (((shiftStart + gracePeriodMillis) > punchin) && (punchin < shiftStart)){
                //Within grace period
                this.adjustedStamp.set(Calendar.MINUTE, shiftstart2.get(Calendar.MINUTE));
                this.adjustedStamp.set(Calendar.HOUR_OF_DAY, shiftstart2.get(Calendar.HOUR_OF_DAY));
                this.adjustedStamp.set(Calendar.SECOND, shiftstart2.get(Calendar.SECOND));
                
                //this.adjustedStamp.setTimeInMillis(shiftStart);
                this.adjustedRule = "Shift Start";
            }
            
            /* if(((shiftStart + gracePeriod) < punchin) && (shiftStart + intervalMillis) > punchin){
                //After grace period
                this.adjustedStamp.setTimeInMillis(shiftStart + intervalMillis);
                this.adjustedRule = "Shift Dock";
            } */
            
            else if(((shiftStart) > punchin) && (shiftStart - intervalMillis) < punchin){
                //Before start of shift
                this.adjustedStamp.set(Calendar.MINUTE, shiftstart2.get(Calendar.MINUTE));
                this.adjustedStamp.set(Calendar.HOUR_OF_DAY, shiftstart2.get(Calendar.HOUR_OF_DAY));
                this.adjustedStamp.set(Calendar.SECOND, shiftstart2.get(Calendar.SECOND));
                //this.adjustedStamp.setTimeInMillis(shiftStart);
                this.adjustedRule = "Shift Start";
            }
            /* else if(((shiftStart + gracePeriod) < punchin) && (shiftStart + intervalMillis) > punchin){
                //After grace period
                this.adjustedStamp.setTimeInMillis(shiftStart + intervalMillis);
                this.adjustedRule = "Shift Dock";
            }  */
                //Lunch Stop
            else if ((lunchStart < punchin) && (lunchStop > punchin)){
                this.adjustedStamp.setTimeInMillis(lunchStop);
                this.adjustedRule = "Lunch Stop";
            }
            else {
                //Round up to adjusted timeframe (15)
                 
                //Minutes in the hour
                int min = this.originalStamp.get(Calendar.MINUTE);
                 
                //Check moving up
                boolean adjustUp = min % interval > interval;
                 
                //Adjustment amount 
                int adjustAmount = (min % interval - interval);
                         
                //If it is being rounded up
                if(adjustUp) {
                    this.adjustedStamp.add(Calendar.MINUTE, adjustAmount);
                }
                //If it is being rounded down
                else {
                    this.adjustedStamp.add(Calendar.MINUTE, - adjustAmount);
                }
                    
                this.adjustedStamp.set(Calendar.SECOND, 0);
                this.adjustedRule = "None";
            }
           
        }
    
        
        //ShiftStop
        else if (this.punchTypeID == CLOCKED_OUT) {
            
           //If it is within the grace period
            if (((shiftStop - gracePeriodMillis) < punchin) && (shiftStop > punchin)) {
                this.adjustedStamp.setTimeInMillis(shiftStop);
                this.adjustedRule = "Shift Stop";
                }
            
            //After the end of the shift
            else if (((shiftStop < punchin) && (shiftStop + intervalMillis) > punchin)) {
                this.adjustedStamp.setTimeInMillis(shiftStop);
                this.adjustedRule = "Shift Stop";
                }
            
            //After Grace Period
            else if (((shiftStop - gracePeriod) > punchin) && (shiftStop - intervalMillis) < punchin) {
                this.adjustedStamp.setTimeInMillis(shiftStop - intervalMillis);
                this.adjustedRule = "Shift Dock";
                }
            
            //Lunch Start
            else if ((lunchStop > punchin) && (lunchStart < punchin)) {
                this.adjustedStamp.setTimeInMillis(lunchStart);
                this.adjustedRule = "Lunch Start";
                }
            
            else {
                //Round up to adjusted timeframe (15)
                 
                //Minutes in the hour
                int min = this.originalStamp.get(Calendar.MINUTE);
                 
                //Check moving up
                boolean adjustUp = min % interval > interval/2;
                 
                //Adjustment amount 
                int adjustAmount = (min%interval);
                         
                //If it is being rounded up
                if(adjustUp){
                    this.adjustedStamp.add(Calendar.MINUTE, adjustAmount);
                }
                //If it is being rounded down
                else{
                    this.adjustedStamp.add(Calendar.MINUTE, - adjustAmount);
                }
                    
                this.adjustedStamp.set(Calendar.SECOND, 0);
                this.adjustedRule = "None";
            }
        }
        
     }
        
   }


    


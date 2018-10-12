/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tas_fa18;

import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
/**
 *
 * @author Chao
 */
public class Shift {
    
    public int ID;
    public String Description;
    public Time Start;
    public Time Stop;
    public int Internal;
    public int Graceperiod;
    public int Dock;
    public Time LunchStart;
    public Time LunchStop;
    public int LunchDecduct; 
    
    
    public Shift(){
        
        ID= ID;
        Description = "";
        Start = new GregorianCalendar();
        Stop = new GregorianCalendar();
        Internal = 0;
        Graceperiod=0;
        Dock= 0;
        LunchStart = new GregorianCalendar();
        LunchStop =new GregorianCalendar();
        LunchDecduct= 0;
        
}    
    
    public int getID(){
        return ID;
    }
    
    public String getDescription(){
        return Description;
    }
    public GregorianCalendar getSatrt(){
        return Start;
    }
    public GregorianCalendar getStop(){
        return Stop;
    }
    public int getInternal(){
        return Internal;
    }
    public int getGraceperiod(){
        return Graceperiod;
    }
    
}


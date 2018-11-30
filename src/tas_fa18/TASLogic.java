package tas_fa18;

import java.util.ArrayList;

public class TASLogic {
    
    private final static int MINUTECONVERSION = 60000;
    
    public static void TASLogic(String[] args) {
        
        TASDatabase db = new TASDatabase();
        
        
    }
    
    public static int calculateTotalMinutes(ArrayList<Punch> punchList, Shift shift){
        
        boolean clockIn;
        Punch previousPunch = null;
        int timeTotal = 0;
        
        //Get Lunch time from shift object
        long lunchTime = (shift.getLunchStop().getTimeInMillis()-shift.getLunchStart().getTimeInMillis())/MINUTECONVERSION;
        
        //Loop through the punches
        for(Punch punch: punchList){
            
            //Boolean
            clockIn = punch.getPunchtypeid()==punch.CLOCKED_IN;
            
            //Log the punch to be subtracted later
            if(clockIn){
                previousPunch = punch;
            }
            //Subtract the punches from each other
            else{
                //Success case
                if(previousPunch!=null){
                    //Calculate the time worked
                    long indivTimeWorked = (punch.getAdjustedtimestamp()-previousPunch.getAdjustedtimestamp())/MINUTECONVERSION;
                    
                    System.out.println(indivTimeWorked);
                    //System.out.println(lunchTime);
                    //System.out.println(shift.getLunchDeduct());
                    
                    //Deduct lunch
                    if(indivTimeWorked > shift.getLunchDeduct()){
                        indivTimeWorked -= lunchTime;
                    }
                    
                    //Add time to the total
                    timeTotal+= indivTimeWorked;
                }
                //Null error
                else{
                    System.out.print("Fatal Error: No initial clock in");
                    return -1;
                }
            }
            
        }
    
        //System.out.println(timeTotal/60000);
        return timeTotal;
    }
    
}

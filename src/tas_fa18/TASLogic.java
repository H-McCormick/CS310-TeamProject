package tas_fa18;

import java.util.ArrayList;

public class TASLogic {
    
    public static void TASLogic(String[] args) {
        
        TASDatabase db = new TASDatabase();
        
        
    }
    
    public static int calculateTotalMinutes(ArrayList<Punch> punchList, Shift shift){
        
        boolean clockIn;
        Punch previousPunch = null;
        int timeTotal = 0;
        
        //Loop through the punches
        for(Punch punch: punchList){

    //DEBUG
            /*System.out.println(punch.getAdjustedtimestamp());
            System.out.println(punch.getPunchtypeid());
            System.out.println(shift.getLunchStart().getTimeInMillis());
            System.out.println(shift.getLunchStop().getTimeInMillis());//*/
            
            //Boolean
            clockIn = punch.getPunchtypeid()==punch.CLOCKED_IN;
            
            //Log the punch to be subtracted later
            if(clockIn){
                previousPunch = punch;
            }
            //Subtract the punches from each other
            else{
                //Null check 
                if(previousPunch!=null){
                    timeTotal+= (punch.getAdjustedtimestamp()-previousPunch.getAdjustedtimestamp())/60000;
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

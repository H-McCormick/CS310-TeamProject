/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package tas_fa18;
import tas_fa18.Shift;
import tas_fa18.TASDatabase;
import tas_fa18.Punch;
import tas_fa18.Badge;

/**
 *
 * @author SMRos
 */
public class test {
    
    public static void main(String[] args){
        
        TASDatabase db = new TASDatabase();
        
        db.getDailyPunchList(db.getBadge("12565C60"),1533551422);
    }
    
    
}

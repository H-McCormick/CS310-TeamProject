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

    public Punch() {
            this.id = 0;
            this.termID = 0;
            this.badgeID = null;
            this.originalStamp = new GregorianCalendar();
            this.adjustedStamp = null;
            this.punchTypeID = 0;

    }

    public Punch(Badge badge, int terminalid, int punchtypeid){
        
    }

}

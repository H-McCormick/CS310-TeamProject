package tas_fa18;
import java.sql.*;
import java.util.GregorianCalendar;


/*
 * @author Sabrina Rosenbaum (github.com/SMRose1998)
 */


public class TASDatabase {
    
    Connection conn = null;
    
    public TASDatabase(){
        
        try {
            
            /* Identify the Server */
            
            String server = ("jdbc:mysql://localhost/tas");
            String username = "tasuser";
            String password = "teamb";
            System.out.println("Connecting to " + server + "...");
            
            /* Load the MySQL JDBC Driver */
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            /* Open Connection */

            conn = DriverManager.getConnection(server, username, password);
            System.out.println("Connection Established!");
            
        /*Catch Error*/
        }catch(Exception e){
            System.out.print("(TASDatabase.constructor()) System Error: "+e);
        }
    }
    
    public Badge getBadge(String id){
        try{
            //Set Querry
            String query = "SELECT * FROM `badge` WHERE `id` = ?";
            
            //Initiate Statement
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            //Set String
            statement.setString(1, id);
            
            //Execute Querry
            if(statement.execute()){
                
               
                //Set results
                ResultSet results = statement.getResultSet();
                results.next();
                
                //Get the name from the results
                String name = results.getString("description");
                
                Badge badge = new Badge(id, name);
               
                return badge;
            }
            
            //Print Querry Error
            System.out.println("Query Failure: "+statement.toString());
            return null;
            
            
        /*Catch SQL Error*/
        }catch(Exception e){
            System.out.print("(TASDatabase.getBadge()) System Error: "+e);
            return null;
        }
    }
    
    public int insertPunch(Punch punch){
        
        //Get the values of punch and convert to strings
        String id = Integer.toString(punch.getID());
        String terminal = Integer.toString(punch.getTerminalid());
        String badge = punch.getBadgeid();
        String originalstamp = punch.printFormattedOriginalTimestamp();
        //GregorianCalendar adjustedstamp = punch.getAdjustedStamp();
        String punchtype = Integer.toString(punch.getPunchtypeid());
        
        
        try{
        //Set query
        String query = "INSERT INTO `punch` (terminalid, badgeid, originaltimestamp, punchtypeid) VALUES(?, ?, ?, ?)";
        
        //Set Statement
        PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        //Set Query Strings
        statement.setString(1, terminal);
        statement.setString(2, badge);
        statement.setString(3, originalstamp);
        statement.setString(4, punchtype);
        
        //Execute Statement
        if(statement.execute()){
            ResultSet result = statement.getResultSet();
            return Integer.parseInt(result.getString("id"));
            
        }else{
            System.out.println("Error: Insert Punch Execution failure");
            return -1;
        }
        
        
        
        }catch(Exception e){
            System.out.println("(TASDatabase.insertPunch()) System Error: "+e);
            return -1;
        }
    }
    
    public Punch getPunch(int id){
        
        try{
            //Set Querry
            String query = "SELECT *, UNIX_TIMESTAMP(`originaltimestamp`) * 1000 AS `timestamp` FROM `punch` WHERE `id` = ?";
            
            //Initiate Statement
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            //Set String
            statement.setString(1, Integer.toString(id));
            
            //Execute Querry
            if(statement.execute()){
                
               
                //Set results
                ResultSet results = statement.getResultSet();
                results.next();
                
                //Get the data from the results
                int termId = Integer.parseInt(results.getString("terminalid"));
                
                String badgeId = results.getString("badgeid");
                
                GregorianCalendar time = new GregorianCalendar();
                time.setTimeInMillis(Long.parseLong(results.getString("timestamp")));
                
                int punchType = Integer.parseInt(results.getString("punchtypeid"));
                
                
                Punch punch = new Punch(id, termId, badgeId, time, punchType);
                
                return punch;
            }
            
            //Print Querry Error
            System.out.println("Query Failure: "+statement.toString());
            return null;
            
            
        /*Catch SQL Error*/
        }catch(Exception e){
            System.out.println("(TASDatabase.getPunch()) System Error: "+e);
            return null;
        }
        
    }
    
    public Shift getShift(Badge badge){
        
        String badgeId = badge.getID();
        
        try{
            
            //GET EMPLOYEE
            
            String getEmployeeQuery = "SELECT `shiftid` FROM `employee` WHERE `badgeid` = ?";
            
            //Prepare statement
            PreparedStatement employeePS = conn.prepareStatement(getEmployeeQuery, Statement.RETURN_GENERATED_KEYS);
            
            //Set the Vars
            employeePS.setString(1, badgeId);
            
            //Initialize the shiftId
            String shiftId = "";
            
            //Querry Employee database
            if(employeePS.execute()){
                ResultSet result = employeePS.getResultSet();
                result.next();
                shiftId = result.getString("shiftid");
            }
            
            //GET SHIFT
            //Set Querry
            String query = "SELECT *, "
                    + "UNIX_TIMESTAMP(`start`) * 1000 AS `st` ,"
                    + "UNIX_TIMESTAMP(`stop`) * 1000 AS `sp` ,"
                    + "UNIX_TIMESTAMP(`lunchstart`) * 1000 AS `stlunch`,"
                    + "UNIX_TIMESTAMP(`lunchstop`) * 1000 AS `splunch`"
                    + "FROM `shift` WHERE `id` = ?";
            
            //Initiate Statement
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            //Set String
            statement.setString(1, shiftId);
            
            //Execute Querry
            if(statement.execute()){
                
                //Set results
                ResultSet results = statement.getResultSet();
                results.next();
                
                //Get the data from the results
                int intId = Integer.parseInt(shiftId);
                
                String description = results.getString("description");
                
                GregorianCalendar start = new GregorianCalendar();
                start.setTimeInMillis(Long.parseLong(results.getString("st")));
                
                GregorianCalendar end = new GregorianCalendar();
                end.setTimeInMillis(Long.parseLong(results.getString("sp")));
                
                int interval = Integer.parseInt(results.getString("interval"))
                        ;
                int grace = Integer.parseInt(results.getString("graceperiod"));
                
                int dock = Integer.parseInt(results.getString("dock"));
                
                GregorianCalendar lunchstart = new GregorianCalendar();
                lunchstart.setTimeInMillis(Long.parseLong(results.getString("stlunch")));
                
                GregorianCalendar lunchend = new GregorianCalendar();
                lunchend.setTimeInMillis(Long.parseLong(results.getString("splunch")));
                
                int lunchdec = Integer.parseInt(results.getString("lunchdeduct"));
               
                //Return shift
                Shift shift = new Shift(intId, description, start, end, interval, grace, dock, lunchstart, lunchend, lunchdec);
               
                return shift;
            }
            
            //Print Querry Error
            System.out.println("Query Failure: "+statement.toString());
            return null;
            
            
        /*Catch SQL Error*/
        }catch(Exception e){
            System.out.println("(TASDatabase.getShift()) System Error: "+e);
            return null;
        }
    }
    
    public Shift getShift(int id){
        try{
            //Set Querry
            String query = "SELECT *, "
                    + "UNIX_TIMESTAMP(`start`) * 1000 AS `st` ,"
                    + "UNIX_TIMESTAMP(`stop`) * 1000 AS `sp` ,"
                    + "UNIX_TIMESTAMP(`lunchstart`) * 1000 AS `stlunch`,"
                    + "UNIX_TIMESTAMP(`lunchstop`) * 1000 AS `splunch`"
                    + "FROM `shift` WHERE `id` = ?";
            
            //Initiate Statement
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            //Set String
            statement.setString(1, Integer.toString(id));
            
            //Execute Querry
            if(statement.execute()){
                
                //Set results
                ResultSet results = statement.getResultSet();
                results.next();
                
                //Get the data from the results
                int intId = id;
                
                String description = results.getString("description");
                
                GregorianCalendar start = new GregorianCalendar();
                start.setTimeInMillis(Long.parseLong(results.getString("st")));
                
                GregorianCalendar end = new GregorianCalendar();
                end.setTimeInMillis(Long.parseLong(results.getString("sp")));
                
                int interval = Integer.parseInt(results.getString("interval"))
                        ;
                int grace = Integer.parseInt(results.getString("graceperiod"));
                
                int dock = Integer.parseInt(results.getString("dock"));
                
                GregorianCalendar lunchstart = new GregorianCalendar();
                lunchstart.setTimeInMillis(Long.parseLong(results.getString("stlunch")));
                
                GregorianCalendar lunchend = new GregorianCalendar();
                lunchend.setTimeInMillis(Long.parseLong(results.getString("splunch")));
                
                int lunchdec = Integer.parseInt(results.getString("lunchdeduct"));
               
                //Return shift
                Shift shift = new Shift(intId, description, start, end, interval, grace, dock, lunchstart, lunchend, lunchdec);
               
                return shift;
            }
            
            //Print Querry Error
            System.out.println("Query Failure: "+statement.toString());
            return null;
            
            
        /*Catch SQL Error*/
        }catch(Exception e){
            System.out.println("(TASDatabase.getShift()) System Error: "+e);
            return null;
        }
    }
    
    
}

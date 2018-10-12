package tas_fa18;
import java.sql.*;

/*
 * @author Sabrina Rosenbaum (github.com/SMRose1998)
 */


public class TASDatabase {
    
    Connection conn = null;
    
    TASDatabase(){
        
        try {
            
            /* Identify the Server */
            
            String server = ("jdbc:mysql://localhost/tas");
            String username = "tasuser";
            String password = "password";
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
    
    public String getBadge(String id){
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
               
                return name;
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
    
    public String getPunch(String id){
        
        try{
            //Set Querry
            String query = "SELECT *, UNIX_TIMESTAMP(`originaltimestamp`) * 1000 AS `timestamp` FROM `punch` WHERE `id` = ?";
            
            //Initiate Statement
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            
            //Set String
            statement.setString(1, id);
            
            //Execute Querry
            if(statement.execute()){
                
               
                //Set results
                ResultSet results = statement.getResultSet();
                results.next();
                
                //Get the data from the results
                String termId = results.getString("terminalid");
                String badgeId = results.getString("badgeid");
                String time = results.getString("timestamp");
                String punchType = results.getString("punchtypeid");
                
               
                return time;
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
    
    public String getShift(String id){
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
            statement.setString(1, id);
            
            //Execute Querry
            if(statement.execute()){
                
                //Set results
                ResultSet results = statement.getResultSet();
                results.next();
                
                //Get the data from the results
                String start = results.getString("st");
                
               
                return start;
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
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tas_fa18;

/**
 *
 * @author ABelj
 */
public class Badge {
    private String id;
    private String description;
    
    
    
    public Badge (String id, String description){
        this.id = id ;
        this. description = description;
    }
    
    public String getID(){
        return this.id;
    }
    public void setID(String newid){
        this.id = newid;
    }
    
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String newDesc){
        this.description = newDesc;
    }
}

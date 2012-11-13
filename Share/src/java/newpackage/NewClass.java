/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package newpackage;

/**
 *
 * @author test
 */
public class NewClass {
    
    private String fName;
    private String lName;
    
    
    public void setFName (String fName){
        this.fName = fName;
    }
    
    public String getFName (){
        return this.fName;
    }
    
    
    public void setLName (String lName){
        this.lName = lName;
    }
    
    public String getLName(){
        return this.lName;
    }
        
    //public String fullName = getFullName();
    public String getFullName() {
        return this.fName + " " + this.lName;
    }
}
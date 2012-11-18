/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bahcohortproj.wdywts;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author ed
 */
@Entity
public class UserDetail implements Serializable {
    
    @Id
    @GeneratedValue
    private int userID;
    
    private String fName;
    private String lName;
    
    
   
    //public String fullName = getFullName();
    public String getFullName() {
        return this.getfName() + " " + this.getlName();
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the fName
     */
    public String getfName() {
        return fName;
    }

    /**
     * @param fName the fName to set
     */
    public void setfName(String fName) {
        this.fName = fName;
    }

    /**
     * @return the lName
     */
    public String getlName() {
        return lName;
    }

    /**
     * @param lName the lName to set
     */
    public void setlName(String lName) {
        this.lName = lName;
    }
}
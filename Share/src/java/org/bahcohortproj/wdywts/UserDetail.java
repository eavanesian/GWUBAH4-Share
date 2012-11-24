/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bahcohortproj.wdywts;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
/**
 *
 * @author ed
 */
@Entity
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="userDetail.byUserName", query="FROM UserDetail where userName = :userName ")
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class UserDetail implements Serializable {
    
    @Id
    @GeneratedValue
    private int userId;
    
    @Column (name="FirstName")
    private String fName;
    
    @Column (name="LastName")
    private String lName;
    
    @Column (name="Username")
    private String userName;
    
    @Column (name="Admin")
    private boolean admin;
    
    
    
    //public String fullName = getFullName();
    public String getFullName() {
        return this.getfName() + " " + this.getlName();
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

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the admin
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }



}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    
    
    private int userId;
    private String fName;
    private String lName;
    private String userName;
    private boolean admin;
    
    //private Collection<UserItems> userItems = new ArrayList<UserItems>();
    
    
    /*public UserDetail(){
        
    }
    
    
    public UserDetail(String fName, String lName, String userName, boolean admin){
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.admin = admin;
    }
    
    
    public UserDetail(String fName, String lName, String userName, boolean admin, Collection<UserItems> userItems){
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.admin = admin;
        this.userItems = userItems;
    } */
    
    
    
     /**
     * @return the userId
     */
    @Id
    @GeneratedValue
    public int getUserId() {
        return this.userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    /**
     * @return the fName
     */
    @Column (name="firstName")
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
    @Column (name="lastName")
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
     * @return the userName
     */
    @Column (name="userName")
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
    @Column (name="admin")
    public boolean isAdmin() {
        return admin;
    }

    /**
     * @param admin the admin to set
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * @return the userItems
     * /
    @OneToMany(fetch=FetchType.LAZY, mappedBy="userItemsId.userDetail", cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    public Collection<UserItems> getUserItems() {
        return this.userItems;
    }

    /**
     * @param userItems the userItems to set
     * /    
    public void setUserItems(Collection<UserItems> userItems) {
        this.userItems = userItems;
    }*/

     
    //public String fullName = getFullName();
    @Transient
    public String getFullName() {
        return this.getfName() + " " + this.getlName();
    }

    

    public String getUserNameFromUserId (int _userId){
        SessionFactory sf = new HibernateUtil().getSessionFactory();        
        Session hSession = sf.openSession();
        hSession.beginTransaction();
        
        UserDetail user = new UserDetail();
        user = (UserDetail) hSession.get(UserDetail.class, _userId);
        return user.getUserName();
    }
    
    
}
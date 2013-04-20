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
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author jay
 */
@Entity
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="itemDetail.byItemName", query="FROM ItemDetail where itemName = :itemName ")
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class ItemDetail implements Serializable {
    
    
    private int itemID;
    private String itemName;
    private String itemDescription;
    private int categoryID;
    private int userID;
    private boolean available;
    
    //private Collection<UserItems> userItems = new ArrayList<UserItems>();
    
    
    public ItemDetail(){
        
    }
    
    public ItemDetail(String itemName, String itemDescription, int categoryID){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.categoryID = categoryID;
    }
    
    /*public ItemDetail(String itemName, String itemDescription, int categoryID, Collection<UserItems> userItems){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.categoryID = categoryID;
        this.userItems = userItems;
    }*/
    
    @Id
    @GeneratedValue
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the itemName
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @param itemName the itemName to set
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @return the itemDescription
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * @param itemDescription the itemDescription to set
     */
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    
    /**
     * @return the userItems
     * /
    @OneToMany(fetch=FetchType.LAZY, mappedBy="userItemsID.itemDetail", cascade=CascadeType.ALL)
    //@Fetch(FetchMode.JOIN)
    public Collection<UserItems> getUserItems() {
        return userItems;
    }

    /**
     * @param userItems the userItems to set
     * /
    public void setUserItems(Collection<UserItems> userItems) {
        this.userItems = userItems;
    } */

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
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        if ((available != true) && (available != false)){
            available = true;
        }
        this.available = available;
    }
    
    
}

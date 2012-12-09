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
//@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@NamedQuery(name="itemDetail.byItemName", query="FROM ItemDetail where itemName = :itemName ")
//@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class ItemDetail implements Serializable {
    
    
    private int itemId;
    private String itemName;
    private String itemDescription;
    private int categoryId;
    private int userId;
    private boolean available;
    
    //private Collection<UserItems> userItems = new ArrayList<UserItems>();
    
    
    public ItemDetail(){
        
    }
    
    public ItemDetail(String itemName, String itemDescription, int categoryId){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.categoryId = categoryId;
    }
    
    /*public ItemDetail(String itemName, String itemDescription, int categoryId, Collection<UserItems> userItems){
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.categoryId = categoryId;
        this.userItems = userItems;
    }*/
    
    @Id
    @GeneratedValue
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    
    /**
     * @return the userItems
     * /
    @OneToMany(fetch=FetchType.LAZY, mappedBy="userItemsId.itemDetail", cascade=CascadeType.ALL)
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
    
    
}

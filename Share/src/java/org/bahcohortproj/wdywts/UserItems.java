/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author ed
 */

/*@AssociationOverrides({
    @AssociationOverride(name="userItemsID.userDetail",
                        joinColumns=@JoinColumn(name="userID")),
    @AssociationOverride(name="userItemsID.userItem",
                        joinColumns=@JoinColumn(name="itemID"))
})*/
@Entity
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class UserItems implements Serializable {
    
    //private UserItemsID userItemsID = new UserItemsID();
    private int userItemsID;
    
    //private ItemDetail item;
    //private UserDetail lender;
    //private UserDetail borrower;
    
    private int itemID;//Fkey
    private int lenderID;//Fkey
    private int borrowerID; //Fkey
    
    private Date listedDate;
    private Date requestedDate;
    private Date borrowedDate;
    private Date returnedDate;
    private String lenderComments;
    private int lenderRatingOfBorrower;
    private String borrowerComments;
    private int borrowerRatingOfLender;
    private int status;//0=borrow requested, 1=item lent, 2=item returned.
    
    
    
    public UserItems(){
        
    }
    

    /**
     * @return the userItemsID
     */
    //@EmbeddedID
    @Id
    @GeneratedValue
    public int getUserItemsID() {
        return userItemsID;
    }

    /**
     * @param userItemsID the userItemsID to set
     */
    public void setUserItemsID(int userItemsID) {
        this.userItemsID = userItemsID;
    }
    
    /**
     * @return the item
     * /
    @Transient
    public ItemDetail getItem() {
        return getUserItemsID().getItemDetail();
    }

    /**
     * @param item the item to set
     * /
    public void setItem(ItemDetail item) {
        getUserItemsID().setItemDetail(item);
    }*/
    
    /**
     * @return the lender
     * /
    @Transient
    public UserDetail getLender() {
        return getUserItemsID().getUserDetail();
    }

    /**
     * @param lender the lenderID to set
     * /
    public void setLender(UserDetail lender) {
        getUserItemsID().setUserDetail(lender);
    }*/

    /**
     * @return the borrower
     * /
    @Transient
    public UserDetail getBorrower() {
        return getUserItemsID().getUserDetail();
    }

    /**
     * @param borrower the borrower to set
     * /
    public void setBorrower(UserDetail borrower) {
        getUserItemsID().setUserDetail(borrower);
    }*/

    /**
     * @return the listedDate
     */
    @Temporal(TemporalType.DATE)
    public Date getListedDate() {
        return listedDate;
    }

    /**
     * @param listedDate the listedDate to set
     */
    public void setListedDate(Date listedDate) {
        this.listedDate = listedDate;
    }

    /**
     * @return the borrowedDate
     */
    @Temporal(TemporalType.DATE)    
    public Date getBorrowedDate() {
        return borrowedDate;
    }

    /**
     * @param borrowedDate the borrowedDate to set
     */
    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    /**
     * @return the returnedDate
     */
    @Temporal(TemporalType.DATE)
    public Date getReturnedDate() {
        return returnedDate;
    }

    /**
     * @param returnedDate the returnedDate to set
     */
    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    /**
     * @return the lenderComments
     */
    public String getLenderComments() {
        return lenderComments;
    }

    /**
     * @param lenderComments the lenderComments to set
     */
    public void setLenderComments(String lenderComments) {
        this.lenderComments = lenderComments;
    }

    /**
     * @return the lenderRatingOfBorrower
     */
    public int getLenderRatingOfBorrower() {
        return lenderRatingOfBorrower;
    }

    /**
     * @param lenderRatingOfBorrower the lenderRatingOfBorrower to set
     */
    public void setLenderRatingOfBorrower(int lenderRatingOfBorrower) {
        this.lenderRatingOfBorrower = lenderRatingOfBorrower;
    }

    /**
     * @return the borrowerComments
     */
    public String getBorrowerComments() {
        return borrowerComments;
    }

    /**
     * @param borrowerComments the borrowerComments to set
     */
    public void setBorrowerComments(String borrowerComments) {
        this.borrowerComments = borrowerComments;
    }

    /**
     * @return the borrowerRatingOfLender
     */
    public int getBorrowerRatingOfLender() {
        return borrowerRatingOfLender;
    }

    /**
     * @param borrowerRatingOfLender the borrowerRatingOfLender to set
     */
    public void setBorrowerRatingOfLender(int borrowerRatingOfLender) {
        this.borrowerRatingOfLender = borrowerRatingOfLender;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    
    
    /*
    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        
        UserItems that = (UserItems) o;
        
        if(getUserItemsID() != null ? !getUserItemsID().equals(that.getUserItemsID()) : that.getUserItemsID() != null) {
            return false;
        }
        
        return true;
    }    
    
    @Override
    public int hashCode(){
        return (getUserItemsID() != null ? getUserItemsID().hashCode() : 0);
    }*/
    
    
    

    /**
     * @return the itemID
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * @param itemID the itemID to set
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     * @return the lenderID
     */
    public int getLenderID() {
        return lenderID;
    }

    /**
     * @param lenderID the lenderID to set
     */
    public void setLenderID(int lenderID) {
        this.lenderID = lenderID;
    }

    /**
     * @return the borrowerID
     */
    public int getBorrowerID() {
        return borrowerID;
    }

    /**
     * @param borrowerID the borrowerID to set
     */
    public void setBorrowerID(int borrowerID) {
        this.borrowerID = borrowerID;
    }

    /**
     * @return the requestedDate
     */
    @Temporal(TemporalType.DATE)
    public Date getRequestedDate() {
        return requestedDate;
    }

    /**
     * @param requestedDate the requestedDate to set
     */
    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    
}

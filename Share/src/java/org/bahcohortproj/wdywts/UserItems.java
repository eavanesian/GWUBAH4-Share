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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author ed
 */
@Entity
/*@AssociationOverrides({
    @AssociationOverride(name="userItemsId.userDetail",
                        joinColumns=@JoinColumn(name="userId")),
    @AssociationOverride(name="userItemsId.userItem",
                        joinColumns=@JoinColumn(name="itemId"))
})*/
public class UserItems implements Serializable {
    
    //private UserItemsId userItemsId = new UserItemsId();
    private int userItemsId;
    
    //private ItemDetail item;
    //private UserDetail lender;
    //private UserDetail borrower;
    
    private int itemId;
    private int lenderId;
    private int borrowerId;
    
    private Date listedDate;
    private Date borrowedDate;
    private Date returnedDate;
    private String lenderComments;
    private int lenderRatingOfBorrower;
    private String borrowerComments;
    private int borrowerRatingOfLender;
    private int stauts;
    
    
    
    public UserItems(){
        
    }
    

    /**
     * @return the userItemsId
     */
    //@EmbeddedId
    @Id
    @GeneratedValue
    public int getUserItemsId() {
        return userItemsId;
    }

    /**
     * @param userItemsId the userItemsId to set
     */
    public void setUserItemsId(int userItemsId) {
        this.userItemsId = userItemsId;
    }
    
    /**
     * @return the item
     * /
    @Transient
    public ItemDetail getItem() {
        return getUserItemsId().getItemDetail();
    }

    /**
     * @param item the item to set
     * /
    public void setItem(ItemDetail item) {
        getUserItemsId().setItemDetail(item);
    }*/
    
    /**
     * @return the lender
     * /
    @Transient
    public UserDetail getLender() {
        return getUserItemsId().getUserDetail();
    }

    /**
     * @param lender the lenderId to set
     * /
    public void setLender(UserDetail lender) {
        getUserItemsId().setUserDetail(lender);
    }*/

    /**
     * @return the borrower
     * /
    @Transient
    public UserDetail getBorrower() {
        return getUserItemsId().getUserDetail();
    }

    /**
     * @param borrower the borrower to set
     * /
    public void setBorrower(UserDetail borrower) {
        getUserItemsId().setUserDetail(borrower);
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
     * @return the stauts
     */
    public int getStauts() {
        return stauts;
    }

    /**
     * @param stauts the stauts to set
     */
    public void setStauts(int stauts) {
        this.stauts = stauts;
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
        
        if(getUserItemsId() != null ? !getUserItemsId().equals(that.getUserItemsId()) : that.getUserItemsId() != null) {
            return false;
        }
        
        return true;
    }    
    
    @Override
    public int hashCode(){
        return (getUserItemsId() != null ? getUserItemsId().hashCode() : 0);
    }*/
    
    
    

    /**
     * @return the itemId
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * @param itemId the itemId to set
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * @return the lenderId
     */
    public int getLenderId() {
        return lenderId;
    }

    /**
     * @param lenderId the lenderId to set
     */
    public void setLenderId(int lenderId) {
        this.lenderId = lenderId;
    }

    /**
     * @return the borrowerId
     */
    public int getBorrowerId() {
        return borrowerId;
    }

    /**
     * @param borrowerId the borrowerId to set
     */
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    
}

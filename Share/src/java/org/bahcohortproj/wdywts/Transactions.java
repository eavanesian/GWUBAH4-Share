/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author ed
 */
@Entity
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class Transactions implements Serializable {
    
    private int transactionID;
    private int lenderID;
    private int borrowerID;
    private int itemID;
    private int transactionTypeID;
    private Date transactionDate;
    private int transactionSetID;
    
    public Transactions(){
        
    }
    
        
    /**
     * @return the transactionID
     */
    @Id
    @GeneratedValue
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }
    
    
    
    @Temporal(TemporalType.DATE)
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * @param transactionDate the transactionDate to set
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
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
     * @return the transactionTypeID
     */
    public int getTransactionTypeID() {
        return transactionTypeID;
    }

    /**
     * @param transactionTypeID the transactionTypeID to set
     */
    public void setTransactionTypeID(int transactionTypeID) {
        this.transactionTypeID = transactionTypeID;
    }

    /**
     * @return the transactionSetID
     */
    public int getTransactionSetID() {
        return transactionSetID;
    }

    /**
     * @param transactionSetID the transactionSetID to set
     */
    public void setTransactionSetID(int transactionSetID) {
        this.transactionSetID = transactionSetID;
    }
    
}

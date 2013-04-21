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
public class Feedback implements Serializable{
    
    
    private int feedbackID;
    private int giverID;
    private int receiverID;
    private int transactionID;
    private int rating;
    private String comments;
    private Date feedbackDate;

    /**
     * @return the feedbackID
     */
    @Id
    @GeneratedValue
    public int getFeedbackID() {
        return feedbackID;
    }

    /**
     * @param feedbackID the feedbackID to set
     */
    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    /**
     * @return the giverID
     */
    public int getGiverID() {
        return giverID;
    }

    /**
     * @param giverID the giverID to set
     */
    public void setGiverID(int giverID) {
        this.giverID = giverID;
    }

    /**
     * @return the receiverID
     */
    public int getReceiverID() {
        return receiverID;
    }

    /**
     * @param receiverID the receiverID to set
     */
    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    /**
     * @return the transactionID
     */
    public int getTransactionID() {
        return transactionID;
    }

    /**
     * @param transactionID the transactionID to set
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    /**
     * @return the rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the feedbackDate
     */
    @Temporal(TemporalType.DATE)
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * @param feedbackDate the feedbackDate to set
     */
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
    
    
    
    
}

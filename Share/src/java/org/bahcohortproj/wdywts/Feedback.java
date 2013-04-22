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
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.bahcohortproj.wdywts.HibernateUtil;
import org.hibernate.Query;

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
    private int transactionSetID;
    private int transactionTypeID;
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
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * @param feedbackDate the feedbackDate to set
     */
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
    
    
    public Double getAvgRating (Integer userID) {
              
        SessionFactory sf = new HibernateUtil().getSessionFactory();
        Session hSession = sf.openSession();
        hSession.beginTransaction();
        
        String hql2 = "SELECT AVG(rating) FROM Feedback F WHERE F.receiverID = :userID";
        Query query2 = hSession.createQuery(hql2);
        query2.setParameter("userID", userID);
        Double lenderRating = (Double)query2.uniqueResult();
        
        if (lenderRating == null) {
            return 0.0;
        }
        else{
            return lenderRating;
        }
                
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
    
    
    
    
}

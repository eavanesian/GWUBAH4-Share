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
public class TransactionType implements Serializable {

    private int transactionTypeID;
    private String transactionTypeName;

    /**
     * @return the transactionTypeID
     */
    @Id
    @GeneratedValue
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
     * @return the transactionTypeName
     */
    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    /**
     * @param transactionTypeName the transactionTypeName to set
     */
    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }
    
    
    
}

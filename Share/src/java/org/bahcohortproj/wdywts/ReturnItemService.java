/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author matt
 */
public class ReturnItemService {
    
    
    public boolean returnItem(Transaction _transaction){
        try{

            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();

            hSession.beginTransaction();

            Transaction _t = new Transaction();
            _t = (Transaction) hSession.get(Transaction.class, _transaction.getTransactionID());
            
            Transaction _t2 = new Transaction();

            
            _t2.setLenderID(_t.getLenderID());
            _t2.setBorrowerID(_t.getBorrowerID());
            _t2.setItemID(_t.getItemID());
            _t2.setTransactionTypeID(3);
            _t2.setTransactionDate(new Date());
            
            
            //UserItems _userItem = new UserItems(); // transaction to be read and updated
            //_userItem = (UserItems) hSession.get(UserItems.class, _transaction.getTransactionID());

            //_userItem.setStatus(2);
            //_userItem.setBorrowerComments(_transaction.getBorrowerComments());
            //_userItem.setBorrowerRatingOfLender(_transaction.getBorrowerRatingOfLender());
            //_userItem.setReturnedDate(new Date());
            
            ItemDetail _item = new ItemDetail(); // item being returned, to set available flag
            _item = (ItemDetail) hSession.get(ItemDetail.class, _t.getItemID());
            _item.setAvailable(true);

            hSession.saveOrUpdate(_t2);
            hSession.getTransaction().commit();

            hSession.close();

            return true;
        }
        catch (Exception e){
            return false;
        }
        
    } // end returnItem
    
     public boolean leaveFeedback(Feedback _feedback){
        try{

            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();

            hSession.beginTransaction();

            Feedback _f = new Feedback();
            
            _f.setComments(_feedback.getComments());
            _f.setRating(_feedback.getRating());
            _f.setGiverID(_feedback.getGiverID());
            _f.setReceiverID(_feedback.getReceiverID());
            _f.setFeedbackDate(new Date());
            _f.setTransactionID(_feedback.getTransactionID());

            //_userItem.setLenderComments(_transaction.getLenderComments());
            //_userItem.setLenderRatingOfBorrower(_transaction.getLenderRatingOfBorrower());

            hSession.saveOrUpdate(_f);
            hSession.getTransaction().commit();

            hSession.close();

            return true;
        }
        catch (Exception e){
            return false;
        }
        
    } // end leaveFeedback
    
}

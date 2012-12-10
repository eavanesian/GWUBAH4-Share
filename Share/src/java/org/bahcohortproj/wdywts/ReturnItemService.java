/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

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
    
    
    public boolean returnItem(UserItems _transaction){
        try{

            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();

            hSession.beginTransaction();

            UserItems _userItem = new UserItems(); // transaction to be read and updated
            _userItem = (UserItems) hSession.get(UserItems.class, _transaction.getUserItemsId());

            _userItem.setStatus(2);
            _userItem.setBorrowerComments(_transaction.getBorrowerComments());
            _userItem.setBorrowerRatingOfLender(_transaction.getBorrowerRatingOfLender());
            _userItem.setReturnedDate(new Date());
            
            ItemDetail _item = new ItemDetail(); // item being returned, to set available flag
            _item = (ItemDetail) hSession.get(ItemDetail.class, _userItem.getItemId());
            _item.setAvailable(true);

            hSession.getTransaction().commit();

            hSession.close();

            return true;
        }
        catch (Exception e){
            return false;
        }
        
    } // end returnItem
    
     public boolean leaveFeedback(UserItems _transaction){
        try{

            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();

            hSession.beginTransaction();

            UserItems _userItem = new UserItems(); // transaction to be read and updated
            _userItem = (UserItems) hSession.get(UserItems.class, _transaction.getUserItemsId());

            _userItem.setLenderComments(_transaction.getLenderComments());
            _userItem.setLenderRatingOfBorrower(_transaction.getLenderRatingOfBorrower());

            hSession.getTransaction().commit();

            hSession.close();

            return true;
        }
        catch (Exception e){
            return false;
        }
        
    } // end leaveFeedback
    
}

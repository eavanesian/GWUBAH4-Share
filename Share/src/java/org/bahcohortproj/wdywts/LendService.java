/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author jay
 */
public class LendService {
    
     public boolean createItem (ItemDetail _item) {
        
        try {
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();

            hSession.beginTransaction();

            hSession.saveOrUpdate(_item);

            hSession.getTransaction().commit();
            hSession.close();
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean createCategory (Category _category) {
        
        try {
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();

            hSession.beginTransaction();

            hSession.saveOrUpdate(_category);

            hSession.getTransaction().commit();
            hSession.close();
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
     
    public boolean approveRequest (int _transactionId) {
        try{
            ItemDetail _itemDetail = new ItemDetail();
            UserItems _transaction = new UserItems();
            int _itemId;
            
            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();
            hSession.beginTransaction();
                       
            _transaction = (UserItems) hSession.get(UserItems.class, _transactionId);
            _itemId = _transaction.getItemId();
            _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, _itemId);
            _itemDetail.setAvailable(false);
            _transaction.setBorrowedDate(new Date());
            _transaction.setStatus(1);
            hSession.update(_itemDetail);
            hSession.update(_transaction);
            hSession.getTransaction().commit();
            hSession.close();
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean declineRequest (int _transactionId) {
        try{
            ItemDetail _itemDetail = new ItemDetail();
            UserItems _transaction = new UserItems();
            int _itemId;
            
            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();
            hSession.beginTransaction();
                       
            _transaction = (UserItems) hSession.get(UserItems.class, _transactionId);
            _itemId = _transaction.getItemId();
            _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, _itemId);
            _itemDetail.setAvailable(true);
            
            hSession.update(_itemDetail);
            hSession.delete(_transaction);
     
            hSession.getTransaction().commit();
            hSession.close();
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean validEntry (String input){
        
        if (input == null || "".equals(input.trim())) {
            return false;        
        } else {
            return true;
        }
    }
}

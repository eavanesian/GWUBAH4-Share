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
     
    public boolean approveRequest (int _transactionID) {
        try{
            ItemDetail _itemDetail = new ItemDetail();
            Transaction _transaction = new Transaction();
            int _itemID;
            
            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();
            hSession.beginTransaction();
                       
            _transaction = (Transaction) hSession.get(Transaction.class, _transactionID);
            _itemID = _transaction.getItemID();
            _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, _itemID);
            _itemDetail.setAvailable(false);
            _transaction.setTransactionDate(new Date());
            _transaction.setTransactionTypeID(2);
            
            hSession.update(_itemDetail);
            hSession.update(_transaction);
            hSession.getTransaction().commit();
            hSession.close();
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean declineRequest (int _transactionID) {
        try{
            ItemDetail _itemDetail = new ItemDetail();
            Transaction _transaction = new Transaction();
            int _itemID;
            
            SessionFactory sf = new HibernateUtil().getSessionFactory();
            Session hSession = sf.openSession();
            hSession.beginTransaction();
                       
            _transaction = (Transaction) hSession.get(Transaction.class, _transactionID);
            _itemID = _transaction.getItemID();
            _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, _itemID);
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

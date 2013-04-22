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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ed
 */
public class BorrowService {
    
    //public UserItems borrowItem(UserDetail _userDetail, int _itemID){
    public Transaction borrowItem(UserDetail _userDetail, int _itemID){

        SessionFactory sf = new HibernateUtil().getSessionFactory();
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        
        
        ItemDetail _itemDetail = new ItemDetail();
        _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, _itemID);
        
        
        UserDetail _lender = new UserDetail();
        _lender = (UserDetail) hSession.get(UserDetail.class, _itemDetail.getUserID());
        
        //UserDetail _internalUser = new UserDetail();
        //_internalUser = (UserDetail) hSession.get(UserDetail.class, _userDetail.getUserID());
        
        
        Criteria criteria = hSession
             .createCriteria(Transaction.class)
             .setProjection(Projections.max("transactionSetID"));
        Integer maxSetID = (Integer)criteria.uniqueResult();
        
        
        Transaction _transaction = new Transaction();
        
        
         //UserItems _userItems = new UserItems();
        //_userItems.setBorrower(_userDetail);
        //_userItems.setLender(_lender);
        //_userItems.setItem(_itemDetail);        
        
         _transaction.setLenderID(_lender.getUserID());
         _transaction.setBorrowerID(_userDetail.getUserID());
         _transaction.setItemID(_itemDetail.getItemID());
         _transaction.setTransactionTypeID(1);
         _transaction.setTransactionDate(new Date());
         _transaction.setTransactionSetID(maxSetID+1);
         
         
         
        //_userItems.setBorrowerID(_userDetail.getUserID());
        //_userItems.setLenderID(_lender.getUserID());
        //_userItems.setItemID(_itemDetail.getItemID());
        //_userItems.setRequestedDate(new Date());
                        
        //hSession.saveOrUpdate(_userItems);
        
        hSession.saveOrUpdate(_transaction);
        
        hSession.getTransaction().commit();
        
        hSession.close();
        
        //TODO: need to put in check for success vs fail in saving to return true vs false
        //return _userItems;
        return _transaction;
    }
    
    
}

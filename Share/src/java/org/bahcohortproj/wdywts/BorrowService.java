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
 * @author ed
 */
public class BorrowService {
    
    
    public boolean borrowItem(UserDetail _userDetail, int _itemId){

        SessionFactory sf = new HibernateUtil().getSessionFactory();
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        
        
        ItemDetail _itemDetail = new ItemDetail();
        _itemDetail = (ItemDetail) hSession.get(ItemDetail.class, _itemId);
        
        
        UserDetail _lender = new UserDetail();
        _lender = (UserDetail) hSession.get(UserDetail.class, _itemDetail.getUserId());
        
        //UserDetail _internalUser = new UserDetail();
        //_internalUser = (UserDetail) hSession.get(UserDetail.class, _userDetail.getUserId());
        
         UserItems _userItems = new UserItems();
                
        //_userItems.setBorrower(_userDetail);
        //_userItems.setLender(_lender);
        //_userItems.setItem(_itemDetail);        
        
        _userItems.setBorrowerId(_userDetail.getUserId());
        _userItems.setLenderId(_lender.getUserId());
        _userItems.setItemId(_itemDetail.getItemId());
        _userItems.setRequestedDate(new Date());
                        
        hSession.saveOrUpdate(_userItems);
        
        hSession.getTransaction().commit();
        
        hSession.close();
        
        //TODO: need to put in check for success vs fail in saving to return true vs false
        return true;
    }
    
    
}

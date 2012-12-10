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
    
    
    public UserItems returnItem(UserDetail _userDetail, int _itemId){

        SessionFactory sf = new HibernateUtil().getSessionFactory();
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        
        UserItems _userItems = new UserItems();
        
        _userItems.setStatus(1);
        
        hSession.getTransaction().commit();
        
        hSession.close();
        
        //TODO: need to put in check for success vs fail in saving to return true vs false
        return _userItems;
        
    }
    
    
}

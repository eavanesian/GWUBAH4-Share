/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author jay
 */
public class EditItemsService {
    

    /**
     * Takes a userID and returns the list of items owned by that user
     * @return the _itemList
     */
    public List getItemList(int _userId) {
        ItemDetail item = new ItemDetail();
        SessionFactory sf = new HibernateUtil().getSessionFactory();        
        Session hSession = sf.openSession();

        hSession.beginTransaction();

        Criteria c = hSession.createCriteria(ItemDetail.class);
        c.add(Restrictions.eq("userId", _userId));

        List<ItemDetail> items = (List<ItemDetail>) c.list();
        return items;
    }
    
    public boolean editItem(int _itemId){
        return true;
    }
    
    public boolean deleteItem(int _itemId){
        return true;
    }
    
}

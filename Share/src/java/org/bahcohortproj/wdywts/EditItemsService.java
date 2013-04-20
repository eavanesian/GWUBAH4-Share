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
    public List getItemList(int _userID) {
        ItemDetail item = new ItemDetail();
        SessionFactory sf = new HibernateUtil().getSessionFactory();        
        Session hSession = sf.openSession();

        hSession.beginTransaction();

        Criteria c = hSession.createCriteria(ItemDetail.class);
        c.add(Restrictions.eq("userID", _userID));

        List<ItemDetail> items = (List<ItemDetail>) c.list();
        return items;
    }
    
    public boolean editItem(ItemDetail _itemChanges){
        try{
            ItemDetail _item = new ItemDetail(); // item to be updated
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();
            hSession.beginTransaction();
            _item = (ItemDetail) hSession.get(ItemDetail.class, _itemChanges.getItemID());
            
            _item.setItemName(_itemChanges.getItemName());
            _item.setItemDescription(_itemChanges.getItemDescription());
            _item.setAvailable(_itemChanges.isAvailable());
            hSession.update(_item);
            
            hSession.getTransaction().commit();
            hSession.close();
        }
        catch (Exception e){
            return false;
        }
        
        return true;
    }
    
    public boolean deleteItem(int _itemID){
        try{
            ItemDetail item = new ItemDetail();
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();

            hSession.beginTransaction();
            item = (ItemDetail) hSession.get(ItemDetail.class, _itemID);
            hSession.delete(item);
            hSession.getTransaction().commit();
            hSession.close();
        }
        catch (Exception e){
            return false;
        }
        
        return true;
    }
    
}

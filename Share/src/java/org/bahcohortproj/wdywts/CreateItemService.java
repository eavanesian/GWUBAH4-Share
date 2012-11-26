/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author jay
 */
public class CreateItemService {
    
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
}

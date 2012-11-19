/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author ed
 */
public class RegisterService {
    
    public boolean registerUser (UserDetail _user) {
        
        try {
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();

            hSession.beginTransaction();

            hSession.saveOrUpdate(_user);

            hSession.getTransaction().commit();
            hSession.close();
            
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    
}

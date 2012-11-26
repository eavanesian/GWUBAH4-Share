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
 * @author ed
 * Business service to check login info
 */
public class LoginService {
    
    
    
    
    public LoginService() {
        //TODO get userinfo from db
        
    }
    
    public boolean validEntry (String userName){
        
        if ("username".equals(userName.trim()) ){
            return false;
        } else if (userName == null || "".equals(userName.trim())) {
            return false;        
        } else {
            return true;
        }
    }
    
    public UserDetail getUserInfo (String userName){
        UserDetail user = new UserDetail();
        
        SessionFactory sf = new HibernateUtil().getSessionFactory();
        Session hSession = sf.openSession();
        
        hSession.beginTransaction();
        //hSession = sf.openSession();
        //hSession.beginTransaction();

        
        //Query q = hSession.getNamedQuery("userDetail.byUserName");
        //q.setParameter("userName", _userName);

       /* Query q = hSession.createQuery("FROM UserDetail where userName = :userName ");
        //Query q = hSession.createQuery("SELECT lName FROM UserDetail where userName = :userName ");

        q.setParameter("userName", _userName);
        */
        
        Criteria c = hSession.createCriteria(UserDetail.class);
        c.add(Restrictions.eq("userName", userName));

        //List<UserDetail> users = (List<UserDetail>) q.list();
        //List<String> users = (List<String>) q.list();
        List<UserDetail> users = (List<UserDetail>) c.list();
        
        hSession.close();
        
        //for (String u : users) {
        for (UserDetail u : users) {        
            user = u;
        }
        
        return user;
    }
    
}

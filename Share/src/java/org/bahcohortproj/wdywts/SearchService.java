/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
/**
 *
 * @author jay
 */
public class SearchService {
    
    @SuppressWarnings("empty-statement")
     public List<ItemDetail> getItems (String _itemName) {
        
        try {
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();

            hSession.beginTransaction();
            Query query = hSession.createQuery("from ItemDetail where itemName like :code ");
            
            query.setParameter("code", "%"+_itemName+"%");
            List<ItemDetail> list = (List<ItemDetail>) query.list();
            hSession.getTransaction().commit();
            hSession.close();

            return list;
                        /*
                        if (list.isEmpty()){
                            System.out.println("Sorry, no matches found for: " +_itemName);
                        } else {
                            System.out.print(list.size() + " result");
                            if (list.size()>1){
                                System.out.print("s");
                            }
                            System.out.println(":<br><br>");
                        }
                        
                        //need to modify the following to print search results:
                        //for (int i = 0; i < list.size(); i++) {
                        //    out.println(list.get(i));
                        //}
                        for (ItemDetail ID : list){
                            System.out.println(ID.getUserName() + " has listed a(n) <a href='showItemDetails.jsp?userSearch=" + id.getItemName() + "'>" + id.getItemName() + "</a href><br>");
                        }*/
          
            
        } catch (Exception e) {
            return null;
        }
     }
     
      public List<ItemDetail> getItems (int _categoryID) {
        
        try {
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();

            hSession.beginTransaction();
            Query query = hSession.createQuery("from ItemDetail where categoryID = :code ");
            
            query.setParameter("code", _categoryID);
                        List<ItemDetail> list = (List<ItemDetail>) query.list();
                        hSession.getTransaction().commit();
                        hSession.close();            
            return list;
          
        } catch (Exception e) {
            return null;
        }
     }
     
     public int getCategoryID (String _categoryName) {
        
        try {
            SessionFactory sf = new HibernateUtil().getSessionFactory();        
            Session hSession = sf.openSession();

            hSession.beginTransaction();
            Query query = hSession.createQuery("FROM Category where Name = :code");
            
            query.setParameter("code", _categoryName);
            List<Category> results = (List<Category>) query.list();
            //System.out.println("list.get(0).getName()+testlist(0)");
            hSession.getTransaction().commit();
            hSession.close();
            if (results.isEmpty()){
                return 0;
            } else{
                return results.get(0).getCategoryID();
            }
                                     
        } catch (Exception e) {
            return 0;
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

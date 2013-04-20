/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 *
 * @author ed
 */
@Embeddable
public class UserItemsID implements Serializable {
    
    private UserDetail userDetail;
    private ItemDetail itemDetail;
    
    
    /**
     * @return the userDetail
     */
    @ManyToOne
    public UserDetail getUserDetail() {
        return userDetail;
    }

    /**
     * @param userDetail the userDetail to set
     */
    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    /**
     * @return the itemDetail
     */
    @ManyToOne
    public ItemDetail getItemDetail() {
        return itemDetail;
    }

    /**
     * @param itemDetail the itemDetail to set
     */
    public void setItemDetail(ItemDetail itemDetail) {
        this.itemDetail = itemDetail;
    }
    
    @Override
    public boolean equals (Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        
        UserItemsID that = (UserItemsID) o;
        
        if (userDetail != null ? !userDetail.equals(that.userDetail) : that.userDetail != null) {
            return false;
        }
        if (itemDetail != null ? !itemDetail.equals(that.itemDetail) : that.itemDetail != null) {
            return false;
        }
        
        return true;
    }
    
    @Override
    public int hashCode(){
        int result;
        result = (userDetail != null ? userDetail.hashCode() : 0);
        result = 31 * result + (itemDetail != null ? itemDetail.hashCode() : 0);
        
        return result;
    }
    
}

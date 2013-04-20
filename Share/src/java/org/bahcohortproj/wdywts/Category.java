/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import java.io.Serializable;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author ed
 */
@Entity
@Cacheable
@Cache(usage= CacheConcurrencyStrategy.READ_WRITE)
//@NamedQuery(name="userDetail.byUserName", query="FROM UserDetail where userName = :userName ")
@org.hibernate.annotations.Entity(selectBeforeUpdate=true)
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private int categoryID;
    
    @Column (name="Name")
    private String name;
    
    @Column (name="Description")
    private String description;

    
    /** parentCategoryID should allow to make cat/sub-cat relationships
     *  if parentCategoryID = null, then it is a cat, otherwise, it is a sub-cat 
     *  and should have the categoryID of the parent cat in it.
     */
    @Column (name="ParentCategoryID")
    private int parentCategoryID;

    /**
     * @return the categoryID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID the categoryID to set
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the parentCategoryID
     */
    public int getParentCategoryID() {
        return parentCategoryID;
    }

    /**
     * @param parentCategoryID the parentCategoryID to set
     */
    public void setParentCategoryID(int parentCategoryID) {
        this.parentCategoryID = parentCategoryID;
    }
    
 


}
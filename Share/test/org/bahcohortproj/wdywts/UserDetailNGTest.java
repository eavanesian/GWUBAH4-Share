/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bahcohortproj.wdywts;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ed
 */
public class UserDetailNGTest {
    
    public UserDetailNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getFullName method, of class UserDetail.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        UserDetail instance = new UserDetail();
        
        String expResult = "F L";
        instance.setfName("F");
        instance.setlName("L");
        String result = instance.getFullName();
        assertEquals(result, expResult);
        
    }

}

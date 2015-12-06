/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Jonathan
 */
public class insertPwd extends cmdSession implements cmdHandler{
    //JUnit Testing code
    @Test
    public void myJUnitTester(){
        assertEquals(getTestmessage(),getActualmessage());
    }
    //DB insert hibernate code
    @Override
    public String execute(String strIn, String cmdIn, Date dteIn, Integer intIn) {
      Transaction tx = null;
      Integer passwordID = null;
      //prime testing variable with input data
      setTestmessage(strIn);
      System.out.println(strIn);
      try{
         tx = getSession().beginTransaction();
         PGen pgen = new PGen(strIn, dteIn);
         passwordID = (Integer) getSession().save(pgen); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      //close the connection
      CloseConnection();
      //returns a string value of the index that will later be converted back to an int
      return passwordID.toString();
    }

}

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
    @Test
    public void myJUnitTester(){
        assertEquals(getTestmessage(),getActualmessage());
    }
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
      //confirm data was written to database and send that to the test checking variable
      //actualmessage = listPassword(passwordID);
      CloseConnection();
      return passwordID.toString();
    }

}

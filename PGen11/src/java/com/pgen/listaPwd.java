/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 * This code just returns a single password it is being used with the JUnit example.
 * @author Jonathan
 */
public class listaPwd extends cmdSession implements cmdHandler{
    //DB list a single password
    @Override
    public String execute(String strIn, String cmdIn, Date dteIn, Integer intIn) {
         Transaction tx = null;
      String passTest = null;
      try{
         tx = getSession().beginTransaction();
         PGen password = (PGen)getSession().get(PGen.class, intIn);
         passTest = password.getPword();
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      //close the db connection
      CloseConnection();
    //return the retrieved password 
    return passTest; 
    }
   
}

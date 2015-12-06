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
 *
 * @author Jonathan
 */
public class deletePwd extends cmdSession implements cmdHandler{

    @Override
    public String execute(String strIn, String cmdIn, Date dteIn, Integer intIn) {
        Transaction tx = null;
      try{
         tx = getSession().beginTransaction();
         PGen Password = 
                   (PGen)getSession().get(PGen.class, intIn); 
         getSession().delete(Password); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
    return "Success";
    }
    
}

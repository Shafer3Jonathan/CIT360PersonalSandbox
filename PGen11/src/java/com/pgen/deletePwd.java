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
 * So this contains code to delete a password. this is currently not being used but will be able to be used at a later time.
 * This is a neat example because it implements and extends at the same time
 * @author Jonathan
 */
//implement the interface and inherit from the parent class
public class deletePwd extends cmdSession implements cmdHandler{
    //hibernate code to delete a password from the DB
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
      //return code
    return "Success";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 *
 * @author Jonathan
 */
public class listofPwd extends cmdSession implements cmdHandler{

    @Override
    public String execute(String strIn, String cmdIn, Date dteIn, Integer intIn) {
        Transaction tx = null;
      try{
         tx = getSession().beginTransaction();
         List Passwords = getSession().createQuery("FROM Password").list(); 
         for (Iterator iterator = 
                Passwords.iterator(); iterator.hasNext();){
                PGen Password = (PGen) iterator.next(); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      CloseConnection();
    return "success";
    }
}

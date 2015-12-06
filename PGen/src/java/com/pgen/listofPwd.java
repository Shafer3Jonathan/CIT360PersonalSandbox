/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Now this is an extremely interesting class it lists all of the classes in the table into a JSON object which gets passed back as a string.
 * This can later be converted back into a JSONobject to be used at a future time.
 * This class has yet to be fully implemented but the concepts and ideas are there
 * @author Jonathan
 */
public class listofPwd extends cmdSession implements cmdHandler{
    //list all passwords in the database
    @Override
    public String execute(String strIn, String cmdIn, Date dteIn, Integer intIn) {
      //declare variables
        JSONParser parser=new JSONParser();
        Transaction tx = null;
      try{
         tx = getSession().beginTransaction();
         List Passwords = getSession().createQuery("FROM Password").list(); 
         for (Iterator iterator = 
                Passwords.iterator(); iterator.hasNext();){
                PGen Password = (PGen) iterator.next();
                parser.parse(Passwords.toString());
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      } catch (ParseException ex) {
            Logger.getLogger(listofPwd.class.getName()).log(Level.SEVERE, null, ex);
        }
      //close db connection
      CloseConnection();
     //convert the JSON into a string to be passed back and converted back at a later time. 
    return parser.toString();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

//import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jonathan the Boss McDos of java programming
 */
//@ManagedBean
@Named(value="pgencontroller")
@RequestScoped
public class PGenController implements Serializable {
    /*Declare session variables for use in the PGen Controller Class
    * Calls the HibernateUtil.java class to enable this to create the following objects
    */
    private static SessionFactory factory;
    private Session session;
    private String testdata = "not generated yet";
    
    /*Default Constructor for the PGen Controller Class every time this class is instanciated this method is called*/
    public PGenController(){
        //this attempts to open the Database Connection the the Password database defined in the database connector specified hibernate.cfg.xml
        try{
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
        }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }
    public String getPassword(){
      return "Your freshly generated password is: " + testdata;
    }
    
    public static String pwdGenerator(){
        //Please Place your Password generating code here
        String generatedpass = "fhqgwads";
        return generatedpass;
    }
    /*This Method closes the Hibernate DB connection*/
    private void CloseConnection(){
         session.close(); 
    }
    /*This is how one would add an Password to the Password table of the Password database*/
    public void pwdAdd(){
        String sessionpass = pwdGenerator();
        PGenController EC = new PGenController();
        EC.addPassword(sessionpass);
        testdata = sessionpass;
        //CloseConnection();
    }
    /*This is how one would list the contents of the Password table of the Password database*/
    public void pwdList(){
        PGenController EC = new PGenController();
        EC.listPasswords();
        //CloseConnection();
    }
    /*Use this to delete an Password from the Password table of the Password database*/
    public void pwdDel(int pwdID) {
        PGenController EC = new PGenController();
        EC.deletePassword(pwdID);
        CloseConnection();
    }
    
    /* Method to ADD an PGen */
    private Integer addPassword(String passwd){
      Transaction tx = null;
      Integer passwordID = null;
      try{
         tx = session.beginTransaction();
         PGen pgen = new PGen(passwd);
         passwordID = (Integer) session.save(pgen); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      
      return passwordID;
   }
    /* Method to LIST all Passwords */
    private void listPasswords(){
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List Passwords = session.createQuery("FROM Password").list(); 
         for (Iterator iterator = 
                Passwords.iterator(); iterator.hasNext();){
                PGen Password = (PGen) iterator.next(); 
            //System.out.print("First Name: " + Password.getFirstName()); 
            //System.out.print("  Last Name: " + Password.getLastName()); 
            //System.out.println("  Salary: " + Password.getSalary()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
      CloseConnection();
   }
     
   /* Method to DELETE an Password from the records */
   private void deletePassword(Integer PasswordID){
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         PGen Password = 
                   (PGen)session.get(PGen.class, PasswordID); 
         session.delete(Password); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }
   }
}

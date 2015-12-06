/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * So this is an interesting class. I yet again proved one thing. As another teacher stated that programmers are lazy.
 * So I relied on concepts I have learned in previous java classes and set this class up with one purpose to have it be able to be inheritable. 
 * this contains the so called priming code for hibernate which is needed by all of the interaction classes like insert, delete, list, list all.
 * @author Jonathan
 */
public class cmdSession {
    //Declare Variables
    private static SessionFactory factory;
    private Session session;
    private String testmessage;
    private String actualmessage;
    private String passtest;
    //Build a hibernate session
    cmdSession (){
        try{
            factory = new Configuration().configure().buildSessionFactory();
            session = factory.openSession();
        }catch (Throwable ex) { 
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex); 
        }
    }
    //Session Closer
    public void CloseConnection(){
         session.close(); 
    }
    //Getters and setters section
    
    public static SessionFactory getFactory() {
        return factory;
    }

    public static void setFactory(SessionFactory factory) {
        cmdSession.factory = factory;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getTestmessage() {
        return testmessage;
    }

    public void setTestmessage(String testmessage) {
        this.testmessage = testmessage;
    }

    public String getActualmessage() {
        return actualmessage;
    }

    public void setActualmessage(String actualmessage) {
        this.actualmessage = actualmessage;
    }

    public String getPasstest() {
        return passtest;
    }

    public void setPasstest(String passtest) {
        this.passtest = passtest;
    }
    
}

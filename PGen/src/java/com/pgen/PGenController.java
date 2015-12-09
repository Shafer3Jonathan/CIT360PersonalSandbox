/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This is what the view or the index.xhtml talks to on the java side. In other words this is the controller
 * socket IO is read from here but relies on the Model for it to work completely please see http://www.tutorialspoint.com/jsf/jsf_ajax.htm for more information.
 * Wikipedia states that socket io is achieved when the serialize interface is implemented see this source https://en.wikipedia.org/wiki/Serialization#Example_2
 * I also just implemented another socket io with PService.java just in case the other is not sufficient
 * @author Jonathan
 */
//allows the JSF to communicate with this using this name
@Named(value="pgencontroller")
@RequestScoped
//declare the class
public class PGenController {
    //declare variables 
    
    //this one is used to display a temporary value on the main screen
    private String testdata = "not generated yet";
    //Password adding function which uses multiple technologies
    public void pwdAdd() throws IOException, InterruptedException{
        //declare variables
        String PassID = null;
        int PasswordID = 0;
        //instanciate classes to be used for multithreading and other purposes
        //Function Classes
        PGenModel EC = new PGenModel();
        PGenIntParser ip = new PGenIntParser();
        //Thread Classes
        PGenerator pg = new PGenerator();
        DGenerator dg = new DGenerator();
        //setup the threads
        Thread passgen = new Thread(pg, "passgen");
        Thread dategen = new Thread(dg, "dategen");
        //start the threads and wait for them to finish these do two things generate a password and get a date time stamp
        //FYI the date timestamp is a HTTP URL Connection
        passgen.start();
        dategen.start();
        passgen.join();
        dategen.join();
        //assign variable data in this case this one gets the primary key (index) value for the value that was just inserted
        PassID = EC.cmdRunner(pg.getaPass(), "insert", dg.getDatetime(), null);
        //this attempts to do a parseInt if not it catches the error. I felt this was ok to use in this case as the primary key will always be a number
        PasswordID = ip.PGenIntParser(PassID);
        //this sets the generated password to the variable being used by Socket IO on the front side.
        setPassword(PGenModel.cmdRunner(null, "list", null, PasswordID));
        //JUnit Testing
        Result result = JUnitCore.runClasses(insertPwd.class, PGenIntParser.class, DGenerator.class);
            for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
    }
    //this is what the socket IO connection checks to see if the value changes
    public String getPassword(){
      return "Your freshly generated password is: " + testdata;
    }
    //setter for the testdata variable
    public void setPassword(String sessionpassword){
        testdata = sessionpassword;
    }
}

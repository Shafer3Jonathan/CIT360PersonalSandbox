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
 *
 * @author Jonathan
 */
@Named(value="pgencontroller")
@RequestScoped
public class PGenController {
    private String testdata = "not generated yet";
    public void pwdAdd() throws IOException{
        PasswordGenerator pGenerator = new PasswordGenerator();
        String sessionpass = pGenerator.pwdGenerator();
        PGenModel EC = new PGenModel();
        DateFetch dFetch = new DateFetch();
        int PassID;
        PassID = EC.addPassword(sessionpass, dFetch.DateFetch());
        setPassword(EC.listPassword(PassID));
        Result result = JUnitCore.runClasses(PGenModel.class);
            for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
      System.out.println(result.wasSuccessful());
        //testdata = sessionpass;
        
        EC.CloseConnection();
    }
    public String getPassword(){
      return "Your freshly generated password is: " + testdata;
    }
    
    public void setPassword(String sessionpassword){
        testdata = sessionpassword;
    }
    /*This is how one would list the contents of the Password table of the Password database*/
    public void pwdList(){
        PGenModel EC = new PGenModel();
        EC.listPasswords();
        EC.CloseConnection();
    }
    /*Use this to delete an Password from the Password table of the Password database*/
    public void pwdDel(int pwdID) {
        PGenModel EC = new PGenModel();
        EC.deletePassword(pwdID);
        EC.CloseConnection();
    }
    
    public String pwdDate () throws IOException{   
        DateFetch DF = new DateFetch();
        String DateString;
        DateString = DF.DateFetch().toString();
        return DateString;
    }
}

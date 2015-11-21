/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

import static com.pgen.PGenModel.pwdGenerator;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Jonathan
 */
@Named(value="pgencontroller")
@RequestScoped
public class PGenController {
    private String testdata = "not generated yet";
    public void pwdAdd(){
        String sessionpass = pwdGenerator();
        PGenModel EC = new PGenModel();
        EC.addPassword(sessionpass);
        setPassword(sessionpass);

        //testdata = sessionpass;
        EC.CloseConnection();
    }
    public String getPassword(){
      return "Your freshly generated password is: " + testdata;
    }
    
    private void setPassword(String sessionpassword){
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
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgen;

//import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
/**
 *
 * @author Jonathan the Boss McDos of java programming
 */
//@ManagedBean

public class PGenModel implements Serializable {
    /*Declare session variables for use in the PGen Controller Class
    * Calls the HibernateUtil.java class to enable this to create the following objects
    */
    public static HashMap<String, cmdHandler> hMapCommand = new HashMap<String, cmdHandler>();
    private static String PassInfo;
    public static String cmdRunner(String strIn, String cmdIn, Date dteIn, Integer intIn){
        hMapCommand.put("insert", new insertPwd());
        hMapCommand.put("delete", new deletePwd());
        hMapCommand.put("listmulti", new listofPwd());
        hMapCommand.put("list", new listaPwd());
        
        cmdHandler handler = hMapCommand.get(cmdIn);
        PassInfo = handler.execute(strIn, cmdIn, dteIn, intIn);
        return PassInfo;
    }
    
}

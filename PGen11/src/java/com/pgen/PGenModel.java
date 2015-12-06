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
 * This uses ACP to allow the model to talk to the database follow referenced functions for more details
 * This also contains an example of the Java Collections Framework a hashmap
 * this also needs to be serializable so that the Front side can do the Socket IO connections there is a further explaination on the internet for JSF and JSP:
 * See the tutorialspoint website for further information to understand how I implemented this http://www.tutorialspoint.com/jsf/jsf_ajax.htm
 * Wikipedia states that socket io is achieved when the serialize interface is implemented see this source https://en.wikipedia.org/wiki/Serialization#Example_2
 * @author Jonathan
 */
public class PGenModel implements Serializable {
    //declare session variables
    public static HashMap<String, cmdHandler> hMapCommand = new HashMap<String, cmdHandler>();
    private static String PassInfo;
    //setup ACP command runner code
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
